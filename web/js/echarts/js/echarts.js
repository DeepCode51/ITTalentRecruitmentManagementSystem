$(function(){
     
 
    /* 比例变化*/
    var changedetail = echarts.init(document.getElementById('changedetail'));
    option = {
        tooltip: {
            trigger: 'axis',
            formatter: '{b}</br>{a}: {c}</br>{a1}: {c1}',
			textStyle:{
                        color:"white"
            },
        },
        toolbox: {
            show:false,
            feature: {
                dataView: {show: true, readOnly: false},
                magicType: {show: true, type: ['line', 'bar']},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        legend: {
            data:['',''],
            show:false
        },
        grid:{
            top:'18%',
            right:'5%',
            bottom:'19%',
            left:'5%', 
            containLabel: true
        },
        xAxis: [
            {
                type: 'category',
				fontSize:'15px',
                data: ['2021年','2022年','2023年','2024年','2025年'],
                splitLine:{
                    show:false,
                    lineStyle:{
					   color: ['white'],
					   width: 1,
					   type: 'solid'
				  }
                },
                axisTick: {
                    show: false
                },
                axisLabel:{
					fontSize:'15',
                    textStyle:{
                        color:"black"
                    },
                    lineStyle:{
                        color: 'gray'
                    },
                    alignWithLabel: true,
                    interval:0
                }
            }
        ],
        yAxis: [
            {
                type: 'value',
                name: '嘎嘎', 
				fontSize:'15',
                nameTextStyle:{
					fontSize:'17',
                    color:'#000'
                },
                interval: 5,
                max:50,
                min: 0,
                splitLine:{
                    show:true, 
                    lineStyle:{
                           color: ['#E5F1FB'],
						   width: 0.5,
						   type: 'solid'
                    }
                },
                axisLine: {
                    show:true,
                    lineStyle: {
                        color: '#115372'
                    }
                },
                axisTick: {
                    show: true
                },
                axisLabel:{
					fontSize:'15',
                    textStyle:{
                        color:"#000"
                    },
                    alignWithLabel: true,
                    interval:0

                }
            },
            { 
            }
        ],
        color:"yellow",
        series: [
            {
                name:'test1',
                type:'bar',
                data:[2, 4, 7, 23, 25],
                boundaryGap: '45%',
                barWidth:'40%',

                itemStyle: {
                    normal: {
                        color: function(params) {
                            var colorList = [
                                '#6bc0fb','#7fec9d','#fedd8b','#ffa597','#84e4dd'
                            ];
                            return colorList[params.dataIndex]
                        },label: {
                            show: true,
                            fontSize:'16',
                            position: 'top',
                            formatter: '{c}'
                        }
                    }
                }
            },

            {
                name:'test2',
                type:'line',
                yAxisIndex: 1,
                lineStyle: {
                    normal: {
                        color:'gray'
                    }
                },
                data:[]
            }
        ]
    };
    changedetail.setOption(option);
 
})