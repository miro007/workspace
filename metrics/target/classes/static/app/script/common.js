function convertToGrid(data, cols) {
	return new GridConverter().convert(data, cols)
}
function GridConverter() {
	this.calculateRowsSize = function(data, colSize) {
		return Math.round(data.length / colSize) + 1
	}

	this.convert = function(data, colSize) {
		var result = []
		var colNumber = 0
		var packSize = 0;
		for (i = 0; i < data.length; i++) {
			if (result[packSize] == undefined) {
				result[packSize] = []
			}
			result[packSize].push(data[i])

			colNumber++;
			if (colNumber == colSize) {
				packSize = packSize + 1
				colNumber = 0
			}

		}
		return result
	}

}

Highcharts.setOptions({
	global : {
		useUTC : false
	}
});

function createSummaryChart( values) {
	$(document).ready(function(){
	var chart = $('#chart').highcharts(
			{
				chart : {
					type : 'spline',
					animation : Highcharts.svg, // don't animate in old IE
					marginRight : 10,
					events : {
						load : function() {
 
							// set up the updating of the chart each second
							series = this.series[0];
						}
					}
				},
				title : {
					text : 'Summary chart'
				},
				xAxis : {
					type : 'datetime',
					tickPixelInterval : 150
				},
				yAxis : {
					title : {
						text : 'Value'
					},
					plotLines : [ {
						value : 0,
						width : 1,
						color : '#808080'
					} ]
				},
				tooltip : {
					formatter : function() {
						return '<b>'
								+ this.series.name
								+ '</b><br/>'
								+ Highcharts.dateFormat('%Y-%m-%d %H:%M:%S',
										this.x) + '<br/>'
								+ Highcharts.numberFormat(this.y, 2);
					}
				},
				legend : {
					enabled : true
				},
				exporting : {
					enabled : false
				},
				minRange: 3600 * 1000,
				series : values,//[{name:'mirek',data:[1,3,4]},{name:'maja',data:[12,33,4]}]

			});
	return chart
	})
}

function createStockChart(id,values, MetricValue){
	// Create a timer
	var start = + new Date();

	$(document).ready(function(){
	$('#chart'+id).highcharts('StockChart', {
	    chart: {
			events: {
				load: function(chart) {
						console.log('Built chart at '+ (new Date() - start) +'ms')
				}
			},
			zoomType: 'x'
	    },

	    rangeSelector: {
	        buttons: [{
	            type: 'day',
	            count: 1,
	            text: '1d'
	        }, {
	            type: 'week',
	            count: 1,
	            text: '1w'
	        }, {
	            type: 'month',
	            count: 1,
	            text: '1m'
	        }, {
	            type: 'month',
	            count: 6,
	            text: '6m'
	        }, {
	            type: 'year',
	            count: 1,
	            text: '1y'
	        }, {
	            type: 'all',
	            text: 'All'
	        }],
	        selected: 0
	    },	
	    navigator : {
			adaptToUpdatedData: false
	    },
	    scrollbar: {
			liveRedraw: false
		},

	    xAxis : {
			events : {
				afterSetExtremes : function(e){
					chart = $('#chart'+id).highcharts();
					
					chart.showLoading('Loading data from server...');
					
					MetricValue.query({
						idMetric : id,
						start:Math.round(e.min),
						end: Math.round(e.max)
					}, function(data){
							var result =createChartSeries('name',data)
							var d=result[0].data
							chart.series[0].setData(d);
							chart.hideLoading();
					})	
					
				}
			}
		},
		 legend: {
		        enabled: true,
		    },
		series: values

	});
	})
}
function afterSetExtremes(e) {
alert(e)
	var currentExtremes = this.getExtremes(),
		range = e.max - e.min,
		chart = $('#container').highcharts();
		
	chart.showLoading('Loading data from server...');
	$.getJSON('http://www.highcharts.com/samples/data/from-sql.php?start='+ Math.round(e.min) +
			'&end='+ Math.round(e.max) +'&callback=?', function(data) {
		
		chart.series[0].setData(data);
		chart.hideLoading();
	});
	
}


function createChartSeries(name, data){
	var series = [];
	series[0] = {name:name,data:[]}
	for(i=0;i<data.length;i++){
		series[0].data.push([data[i].date, data[i].value])
	}
	return series;
}