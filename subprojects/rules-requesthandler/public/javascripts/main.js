angular.module('tradeFetchService', [ 'ngResource' ]).factory('TradeService',
		function($resource) {
			return $resource('trades', {}, {
				query : {
					method : 'GET',
					isArray : true
				},
				clear : {
					method : 'DELETE'
				}
			});
		});

angular.module('ruleExecService', [ 'ngResource' ]).factory('RuleService',
		function($resource) {
			return $resource('rules', {}, {
				query : {
					method : 'GET',
					isArray : true
				}
			});
		});

angular.module('tradeGeneratorService', [ 'ngResource' ]).factory(
		'TradeGeneratorService', function($resource) {
			return $resource('tradegenerator', {}, {
				query : {
					method : 'GET',
					isArray : true
				}
			});
		});
angular.module('RuleApp', [ 'ngGrid', 'tradeFetchService', 'ruleExecService',
		'tradeGeneratorService' ]);

function TradeCtrl($scope, TradeService, RuleService, TradeGeneratorService) {

	$scope.totalServerItems = 0;
	$scope.pagingOptions = {
		pageSizes : [ 250, 500, 1000 ],
		pageSize : 250,
		currentPage : 1
	};

	$scope.clearTrades = function() {
		TradeService.clear();
		setTimeout(function() {
			$scope.getPagedDataAsync($scope.pagingOptions.pageSize,
					$scope.pagingOptions.currentPage);
		}, 250);
	};

	$scope.generateTrades = function() {
		TradeGeneratorService.query({
			numTrades : 5000,
			tradeType : "random"
		});
		setTimeout(function() {
			$scope.getPagedDataAsync($scope.pagingOptions.pageSize,
					$scope.pagingOptions.currentPage);
		}, 250);
	}

	$scope.fireRules = function() {
		RuleService.query();
		setTimeout(function() {
			$scope.getPagedDataAsync($scope.pagingOptions.pageSize,
					$scope.pagingOptions.currentPage);
		}, 300);
	};

	$scope.getPagedDataAsync = function(pageSize, page, searchText) {
		$scope.tradeData = TradeService.query({
			pageNum : page,
			tradesPerPage : pageSize
		});
	};

	$scope.getPagedDataAsync($scope.pagingOptions.pageSize,
			$scope.pagingOptions.currentPage);

	$scope.$watch('pagingOptions', function(newVal, oldVal) {
		if (newVal !== oldVal && newVal.currentPage !== oldVal.currentPage) {
			$scope.getPagedDataAsync($scope.pagingOptions.pageSize,
					$scope.pagingOptions.currentPage);
		}
	}, true);

	$scope.$watch('pagingOptions', function(newVal, oldVal) {
		if (newVal !== oldVal && newVal.currentPage !== oldVal.currentPage) {
			$scope.getPagedDataAsync($scope.pagingOptions.pageSize,
					$scope.pagingOptions.currentPage);
		}
	}, true);

	$scope.tradeSelection = [];
	$scope.tradetable = {
		data : 'tradeData',
		enablePaging : true,
		showFooter : true,
		totalServerItems : 'totalServerItems',
		pagingOptions : $scope.pagingOptions,
		selectedItems : $scope.tradeSelection,
		multiSelect : false,
		rowHeight : 20,
		headerRowHeight : 20,
		footerRowHeight : 40,
		columnDefs : [ {
			field : 'id',
			displayName : 'Trade ID',
			width : "75",
			resizable : true
		}, {
			field : 'tradeDate',
			displayName : 'Trade Date',
			width : "155",
			resizable : true
		}, {
			field : 'settleDate',
			displayName : 'Settle Date',
			width : "155",
			resizable : true
		}, {
			field : 'customer',
			displayName : 'Customer',
			width : "155",
			resizable : true
		}, {
			field : 'side',
			displayName : 'Side',
			width : "55",
			resizable : true
		}, {
			field : 'symbol',
			displayName : 'Ticker',
			width : "55",
			resizable : true
		}, {
			field : 'pair',
			displayName : 'Pair',
			width : "75",
			resizable : true
		}, {
			field : 'quantity',
			displayName : 'Qty',
			width : "75",
			resizable : true
		}, {
			field : 'price',
			displayName : 'Price',
			width : "75",
			resizable : true
		}, {
			field : 'params',
			displayName : 'Additional Properties',
			width : "**",
			maxWidth : 300,
			resizable : true
		} ]
	};

};