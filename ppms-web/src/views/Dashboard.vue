<template>
  <div>
    <a-breadcrumb style="margin: 16px 15px; text-align: left">
      <a-breadcrumb-item>主页</a-breadcrumb-item>
      <a-breadcrumb-item>显示面板</a-breadcrumb-item>
    </a-breadcrumb>
    <div class="daily-layout">
      <a-row>
        <a-col :span="12" style="width: 128px">
          <a-avatar :size="128" icon="user" style="float: left" />
        </a-col>
        <a-col :span="12">
          <div style="padding-left: 20px; padding-top: 10px; text-align: left">
            <h2 class="greet">欢迎qinhy，上午好，很高兴见到你</h2>
            <p class="motto" style="padding-top: 20px; font-size: 150%">
              安静的等待时机！
            </p>
          </div>
        </a-col>
      </a-row>
    </div>
    <div class="card-layout">
      <a-row :gutter="16">
        <a-col :span="8">
          <a-card title="本月总收入" :bordered="true">
            <p>¥29999RMB</p>
          </a-card>
        </a-col>
        <a-col :span="8">
          <a-card title="本月总支出" :bordered="true">
            <p>¥7899RMB</p>
          </a-card>
        </a-col>
        <a-col :span="8">
          <a-card title="本月冲动性消费总支出" :bordered="true">
            <p>¥7899RMB</p>
          </a-card>
        </a-col>
      </a-row>
    </div>
    <div class="echarts">
      <a-row>
        <a-col :span="12">
          <div id="expense-echarts"></div>
        </a-col>
        <a-col :span="12">
          <div id="income-echarts"></div>
        </a-col>
      </a-row>
    </div>
  </div>
</template>
<script>
import * as echarts from "echarts";

export default {
  name: "Dashboard",
  created() {
    console.log("created dashboard page");
  },
  mounted() {
    this.drawExpensePic();
    this.drawIncomeBar();
  },
  methods: {
    drawExpensePic() {
      var chartDom = document.getElementById("expense-echarts");
      var myChart = echarts.init(chartDom);
      var option;

      option = {
        title: {
          text: "支出",
          subtext: "分类详情",
          left: "center",
        },
        tooltip: {
          trigger: "item",
        },
        legend: {
          orient: "vertical",
          left: "left",
        },
        series: [
          {
            name: "支出",
            type: "pie",
            radius: "50%",
            data: [
              { value: 1048, name: "房贷" },
              { value: 735, name: "车贷" },
              { value: 580, name: "买菜" },
              { value: 484, name: "外出吃饭" },
              { value: 300, name: "车费" },
            ],
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: "rgba(0, 0, 0, 0.5)",
              },
            },
          },
        ],
      };

      option && myChart.setOption(option);
    },
    drawIncomeBar() {
      var chartDom = document.getElementById("income-echarts");
      var myChart = echarts.init(chartDom);
      var option;

      option = {
        title: {
          text: "月收入",
        },
        tooltip: {},
        legend: {
          data: ["收入"],
        },
        xAxis: {
          data: ["工资", "羊毛衫", "雪纺衫", "裤子", "高跟鞋", "袜子"],
        },
        yAxis: {},
        series: [
          {
            name: "收入",
            type: "bar",
            data: [20000, 1689, 3999, 399, 5000, 20],
          },
        ],
      };

      option && myChart.setOption(option);
    },
  },
};
</script>
<style scoped>
.daily-layout {
  margin: 16px;
  padding: 10px;
  border: 1px solid #d9d9d9;
  border-radius: 6px;
}

.card-layout {
  margin: 16px;
  padding: 10px;
}

.ant-card {
  width: 268px;
  height: 120px;
  background: #f0f2f5;
}

.echarts {
  margin: 16px;
  padding: 10px;
}

.echarts #expense-echarts {
  width: 600px;
  height: 600px;
}

.echarts #income-echarts {
  width: 600px;
  height: 400px;
}
</style>