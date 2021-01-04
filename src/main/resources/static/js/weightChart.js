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

    data.addColumn('date', 'Date of Measure');
    data.addColumn('number', 'Weight');

    for (var i = 0; i < resultArray.length; i++) {
        data.addRows([
            [new Date(resultArray[i][1]["dateOfMeasure"]),
                resultArray[i][1]["weight"]]
        ]);
    }


    var options = {
        backgroundColor: {
            fill:'transparent'
        },
        chartArea:{
            backgroundColor: '#000'
        },
        title: 'Weight chart',
        titleTextStyle:{
            color: '#FFF',
            fontSize: '20',
        },
        legend: {
            textStyle: {
                color: '#FFF',
                fontSize: '15',
            }
        },

        chart:{
            title: 'Weight',
            curveType: 'function',
            legend: {
                position: 'bottom' }
        },
        axisTitlesPosition:'out',
        series: [
            {color: '#d8db12', visibleInLegend: true}],
        hAxis:{
            format: 'MMM yyyy',
            textStyle: {
                color: '#1f9acf',
                bold: true,
            },
        },
        vAxis:{
            textStyle: {
                color: '#1f9acf',
                bold: true,
            }
        }

    };

    var chart = new google.visualization.LineChart(document.getElementById('myWeightChart'));

    chart.draw(data, options);
}

function decodeHtml(html) {
    var txt = document.createElement("textarea");
    txt.innerHTML = html;
    return txt.value;
}
