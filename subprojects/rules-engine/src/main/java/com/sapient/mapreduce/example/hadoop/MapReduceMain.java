package com.sapient.mapreduce.example.hadoop;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.TextOutputFormat;
import org.apache.hadoop.util.Tool;

public class MapReduceMain extends Configured implements Tool
{
    @SuppressWarnings({ "rawtypes" })
    public static void main(String args[]) throws IOException
    {
        JobConf conf = new JobConf(MapReduceMain.class);
        conf.setJobName("Counting USDCAD");

        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(IntWritable.class);

        conf.setMapperClass((Class<? extends org.apache.hadoop.mapred.Mapper>) RuleMapper.class);
        conf.setReducerClass((Class<? extends org.apache.hadoop.mapred.Reducer>) RuleReducer.class);

        conf.setInputFormat(TextInputFormat.class);
        conf.setOutputFormat(TextOutputFormat.class);

        DateFormat df = new SimpleDateFormat("yyyyMMdd-hh.mm.ss");
        String outpath = df.format(new Date()) + "example.output";

        FileInputFormat.setInputPaths(conf, new Path("/user/pparso/trades"));
        FileOutputFormat.setOutputPath(conf, new Path("/user/pparso/" + outpath + "/"));

        JobClient.runJob(conf);
    }

    public static class RuleMapper implements Mapper<LongWritable, Text, Text, IntWritable>
    {
        public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException
        {
            String line = value.toString();
            System.out.println(line);
            if (line.contains("USD/CAD"))
            {
                output.collect(new Text("USD/CAD"), new IntWritable(1));
            }

        }

        @Override
        public void configure(JobConf arg0)
        {
            // TODO Auto-generated method stub

        }

        @Override
        public void close() throws IOException
        {
            // TODO Auto-generated method stub

        }

    }

    public static class RuleReducer implements Reducer<Text, IntWritable, Text, IntWritable>
    {
        public void reduce(Text key, Iterator<IntWritable> values, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException
        {
            int sum = 0;
            while (values.hasNext())
            {
                sum += values.next().get();
            }
            output.collect(key, new IntWritable(sum));
        }

        @Override
        public void configure(JobConf arg0)
        {
            // TODO Auto-generated method stub

        }

        @Override
        public void close() throws IOException
        {
            // TODO Auto-generated method stub

        }
    }

    @Override
    public int run(String[] arg0) throws Exception
    {
        // TODO Auto-generated method stub
        return 0;
    }
}