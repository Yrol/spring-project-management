var decodedChartData =  decodeHtml(chartData);
var chartJsonArray = JSON.parse(decodedChartData);

var numericData = new Array();
var labelData = new Array();

chartJsonArray.forEach(function(item){
  numericData.push(item.projectStageCount);
  labelData.push(item.stage);
});

var config = {
  type: 'pie',
  data: {
    datasets: [{
      data: numericData,
      backgroundColor: ['#FF6384', '#36A2EB','#FFCE56']
    }],
    labels: labelData
  },
  options: {
    responsive: true,
    title: {
        display: true,
        text: 'Project Statuses'
    },
    animation: {
      animateScale: true,
      animateRotate: true
    }
  }
};

new Chart(document.getElementById('myPieChart'), config);

function decodeHtml(html) {
    var txt = document.createElement('textarea');
    txt.innerHTML = html;
    return txt.value;
}