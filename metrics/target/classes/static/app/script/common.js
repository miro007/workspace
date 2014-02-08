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

function createChart(values) {
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
					text : 'Live random data'
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
				series : values

			});
	return chart
}

function createChartSeries(data){
	for(i=0;i<data.length;i++){
		var len =data[i].data.length
		var element =data[i];
		data[i]={name:data[i].name, data:[]}
		for(j=0;j<len;j++) {
			
			data[i].data.push([element.data[j].date, element.data[j].value])
		}
	}
	return data;
}