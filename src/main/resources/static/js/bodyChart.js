var chartDataDecoded2 = decodeHtml2(chart2Data);
var chartJsonArray2 = JSON.parse(chartDataDecoded2)
var resultArrayBicep = [];
var resultArrayThigh = [];
var resultArrayChest = [];
var resultArrayWaist = [];

for(var i in chartJsonArray2){
    resultArrayThigh.push([i, chartJsonArray2[i]]);
}
for(var i in chartJsonArray2){
    resultArrayBicep.push([i, chartJsonArray2[i]]);
}
for(var i in chartJsonArray2){
    resultArrayChest.push([i, chartJsonArray2[i]]);
}
for(var i in chartJsonArray2){
    resultArrayWaist.push([i, chartJsonArray2[i]]);
}

google.charts.load('current', {'packages':['corechart']});
google.charts.setOnLoadCallback(drawChartBicep);
google.charts.setOnLoadCallback(drawChartThigh);
google.charts.setOnLoadCallback(drawChartChest);
google.charts.setOnLoadCallback(drawChartWaist);


function drawChartBicep() {
    var dataBicep = new google.visualization.DataTable();

    dataBicep.addColumn('date', 'Date of Measure');
    dataBicep.addColumn('number', 'Right Bicep');
    dataBicep.addColumn('number', 'Left Bicep');

    for (var i = 0; i < resultArrayBicep.length; i++) {
        dataBicep.addRows([
            [new Date(resultArrayBicep[i][1]["dateOfMeasure"]),
                resultArrayBicep[i][1]["rightBicep"],
                resultArrayBicep[i][1]["leftBicep"]]
        ]);
    }


    var options = {
        backgroundColor: {
            fill:'transparent'
        },
        chartArea:{
            backgroundColor: '#000'
        },
        title: 'Biceps chart',
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
            title: 'Bicep',
            curveType: 'function',
            legend: {
                position: 'bottom' }
        },
        axisTitlesPosition:'out',
        series: [
            {color: '#d8db12', visibleInLegend: true},
            {color: '#30e676', visibleInLegend: true}],
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

    var chartBicep = new google.visualization.LineChart(document.getElementById('myBicepChart'));

    chartBicep.draw(dataBicep, options);
}


function drawChartThigh() {
    var data = new google.visualization.DataTable();

    data.addColumn('date', 'Date of Measure');
    data.addColumn('number', 'Right Thigh');
    data.addColumn('number', 'Left Thigh');

    for (var i = 0; i < resultArrayBicep.length; i++) {
        data.addRows([
            [new Date(resultArrayBicep[i][1]["dateOfMeasure"]),
                resultArrayBicep[i][1]["rightThigh"],
                resultArrayBicep[i][1]["leftThigh"]]
        ]);
    }


    var options = {
        backgroundColor: {
            fill:'transparent'
        },
        chartArea:{
            backgroundColor: '#000'
        },
        title: 'Thighs chart',
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
            title: 'Thigh',
            curveType: 'function',
            legend: {
                position: 'bottom' }
        },
        axisTitlesPosition:'out',
        series: [
            {color: '#d8db12', visibleInLegend: true},
            {color: '#30e676', visibleInLegend: true}],
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

    var chart = new google.visualization.LineChart(document.getElementById('myThighChart'));

    chart.draw(data, options);
}

function drawChartChest() {
    var data = new google.visualization.DataTable();

    data.addColumn('date', 'Date of Measure');
    data.addColumn('number', 'Chest');


    for (var i = 0; i < resultArrayBicep.length; i++) {
        data.addRows([
            [new Date(resultArrayBicep[i][1]["dateOfMeasure"]),
                resultArrayBicep[i][1]["chest"]]
        ]);
    }


    var options = {
        backgroundColor: {
            fill:'transparent'
        },
        chartArea:{
            backgroundColor: '#000'
        },
        title: 'Chest chart',
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
            title: 'Chest',
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

    var chart = new google.visualization.LineChart(document.getElementById('myChestChart'));

    chart.draw(data, options);
}

function drawChartWaist() {
    var data = new google.visualization.DataTable();

    data.addColumn('date', 'Date of Measure');
    data.addColumn('number', 'Waist');


    for (var i = 0; i < resultArrayBicep.length; i++) {
        data.addRows([
            [new Date(resultArrayBicep[i][1]["dateOfMeasure"]),
                resultArrayBicep[i][1]["waist"]]
        ]);
    }


    var options = {
        backgroundColor: {
            fill:'transparent'
        },
        chartArea:{
            backgroundColor: '#000'
        },
        title: 'Waist chart',
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
            title: 'Waist',
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

    var chart = new google.visualization.LineChart(document.getElementById('myWaistChart'));

    chart.draw(data, options);
}

function decodeHtml2(html) {
    var txt = document.createElement("textarea");
    txt.innerHTML = html;
    return txt.value;
}
