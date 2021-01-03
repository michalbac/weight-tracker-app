var chartDataDecoded = decodeHtml(chartData);
var chartJsonArray = JSON.parse(chartDataDecoded)
var resultArray = [];

for(var i in chartJsonArray){
    resultArray.push([i, chartJsonArray[i]]);
}

google.charts.load('current', {'packages':['corechart']});
google.charts.setOnLoadCallback(drawChart);

function drawChart() {
    var data = new google.visualization.DataTable();
    data.addColumn('number', 'Weight');
    data.addColumn('date', 'Date of Measure');

    for (var i = 0; i < resultArray.length; i++) {
        data.addRows([
            [resultArray[i][1]["weight"],
                new Date(resultArray[i][1]["dateOfMeasure"])]
        ]);
    }


    var options = {
        backgroundColor: '#000',
        chartArea:{
            backgroundColor: '#FFF'
        },
        chart:{
            title: 'Weight',
            curveType: 'function',
            legend: { position: 'bottom' }
        },

    };

    var chart = new google.visualization.LineChart(document.getElementById('myWeightChart'));

    chart.draw(data, options);
}

function decodeHtml(html) {
    var txt = document.createElement("textarea");
    txt.innerHTML = html;
    return txt.value;
}
