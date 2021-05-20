<template>
  <div class="income">
    <a-breadcrumb style="margin: 16px 15px; text-align: left">
      <a-breadcrumb-item>主页</a-breadcrumb-item>
      <a-breadcrumb-item>收支管理</a-breadcrumb-item>
      <a-breadcrumb-item>收入</a-breadcrumb-item>
    </a-breadcrumb>
    <div id="component-advanced-search">
      <a-form class="advanced-search-form" :form="form" @submit="handleSearch">
        <a-row :gutter="16" id="search-layout-1">
          <a-col :span="8">
            <a-form-item :label="`开始时间`">
              <a-date-picker placeholder="请选择时间" />
            </a-form-item>
          </a-col>
          <a-col :span="8">
            <a-form-item :label="`结束时间`">
              <a-date-picker placeholder="请选择时间" />
            </a-form-item>
          </a-col>
          <a-col :span="8" :style="{ textAlign: 'right' }">
            <a-form-item :label="`录入者名称`">
              <a-input placeholder="请输入名字" />
            </a-form-item>
          </a-col>
          <a-col :span="8">
            <a-form-item :label="`收入来源`">
              <a-input placeholder="请输入来源" />
            </a-form-item>
          </a-col>
          <a-col :span="8">
            <a-form-item :label="`标题内容`">
              <a-input placeholder="请输入标题内容" />
            </a-form-item>
          </a-col>
          <a-col :span="8">
            <a-form-item :label="`备注内容`">
              <a-input placeholder="请输入备注内容" />
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="24" :style="{ textAlign: 'right' }">
            <a-button type="primary" html-type="submit"> 搜索 </a-button>
            <a-button :style="{ marginLeft: '8px' }" @click="handleReset">
              清空
            </a-button>
          </a-col>
        </a-row>
        <a-form-item></a-form-item>
      </a-form>
    </div>
    <div class="add-layout">
      <a-button type="primary" @click="showExpenseForm">添加</a-button>
      <income-form ref="income" @datas="eventDatas"></income-form>
    </div>
    <a-table :columns="columns" :data-source="data">
      <template
        v-for="col in [
          listHead.title,
          listHead.money,
          listHead.source,
          listHead.remarks,
          listHead.recorder,
          'created',
        ]"
        :slot="col"
        slot-scope="text, record"
      >
        <div :key="col">
          <a-input
            v-if="record.editable"
            style="margin: -5px 0"
            :value="text"
            @change="(e) => handleChange(e.target.value, record.key, col)"
          />
          <template v-else>
            {{ text }}
          </template>
        </div>
      </template>
      <template slot="operation" slot-scope="text, record">
        <div class="editable-row-operations">
          <span v-if="record.editable">
            <a @click="() => save(record.key)">保存</a>
            <a-popconfirm title="确定取消?" @confirm="() => cancel(record.key)">
              <a>取消</a>
            </a-popconfirm>
          </span>
          <span v-else>
            <a :disabled="editingKey !== ''" @click="() => edit(record.key)"
              >编辑</a
            >
          </span>
        </div>
      </template>
    </a-table>
  </div>
</template>


<script>
import incomeForm from "../components/IncomeForm.vue";
import { formatDatetime } from "../utils/dateUtil.js";
import { mapActions } from "vuex";

const dataHeads = {
  title: "标题",
  money: "金额",
  source: "收入来源",
  remarks: "备注",
  recorder: "录入者",
  created: "生成时间",
  operation: "操作",
};

const columns = [
  {
    title: dataHeads.title,
    dataIndex: dataHeads.title,
    scopedSlots: { customRender: dataHeads.title },
  },
  {
    title: dataHeads.money,
    dataIndex: dataHeads.money,
    scopedSlots: { customRender: dataHeads.money },
  },
  {
    title: dataHeads.source,
    dataIndex: dataHeads.source,
    scopedSlots: { customRender: dataHeads.source },
  },

  {
    title: dataHeads.remarks,
    dataIndex: dataHeads.remarks,
    scopedSlots: { customRender: dataHeads.remarks },
  },
  {
    title: dataHeads.recorder,
    dataIndex: dataHeads.recorder,
    scopedSlots: { customRender: dataHeads.recorder },
  },
  {
    title: dataHeads.created,
    dataIndex: dataHeads.created,
    scopedSlots: { customRender: dataHeads.created },
  },
  {
    title: dataHeads.operation,
    dataIndex: "operation",
    scopedSlots: { customRender: "operation" },
  },
];

var data = [];

export default {
  data() {
    this.cacheData = data.map((item) => ({ ...item }));
    return {
      data,
      columns,
      editingKey: "",
      keyCount: 0,
      listHead: {
        titile: dataHeads.title,
        money: dataHeads.money,
        source: dataHeads.source,
        remarks: dataHeads.remarks,
        recorder: dataHeads.recorder,
        created: dataHeads.created,
        operation: dataHeads.operation,
      },
      form: this.$form.createForm(this, { name: "advanced_search" }),
    };
  },
  created() {
    console.log("created income page");
    let datas = this.$store.getters.getIncomeDatas;
    console.log("created datas: ", [...datas]);
    this.data = datas;
    this.keyCount = this.data.length;
    this.cacheData = this.data.map((item) => ({ ...item }));
  },
  computed: {},
  name: "Income",
  components: {
    incomeForm,
  },
  methods: {
    ...mapActions(["AddIncome"]),
    handleChange(value, key, column) {
      const newData = [...this.data];
      const target = newData.filter((item) => key === item.key)[0];
      if (target) {
        target[column] = value;
        this.data = newData;
      }
    },
    edit(key) {
      const newData = [...this.data];
      const target = newData.filter((item) => key === item.key)[0];
      this.editingKey = key;
      if (target) {
        target.editable = true;
        this.data = newData;
      }
    },
    save(key) {
      const newData = [...this.data];
      const newCacheData = [...this.cacheData];
      const target = newData.filter((item) => key === item.key)[0];
      const targetCache = newCacheData.filter((item) => key === item.key)[0];
      if (target && targetCache) {
        delete target.editable;
        this.data = newData;
        Object.assign(targetCache, target);
        this.cacheData = newCacheData;
      }
      this.editingKey = "";
    },
    cancel(key) {
      const newData = [...this.data];
      const target = newData.filter((item) => key === item.key)[0];
      this.editingKey = "";
      if (target) {
        Object.assign(
          target,
          this.cacheData.filter((item) => key === item.key)[0]
        );
        delete target.editable;
        this.data = newData;
      }
    },
    handleSearch(e) {
      e.preventDefault();
      this.form.validateFields((error, values) => {
        console.log("error", error);
        console.log("Received values of form: ", values);
      });
    },
    handleReset() {
      this.form.resetFields();
    },
    showExpenseForm() {
      this.$refs.income.setVisible(true);
    },
    eventDatas(values, callback) {
      const param = {
        title: values.title,
        money: values.price,
        source: values.source,
        recorder: values.recorder,
        remarks: values.remarks,
      };
      this.AddIncome(param).then((res) => {
        if (res.code == 200) {
          var date = values.date;
          let fmtDate = formatDatetime(date, "yyyy-MM-dd hh:mm:sss");
          var showDatas = {
            key: this.keyCount.toString(),
            标题: values.title,
            金额: values.price,
            收入来源: values.expensesType,
            生成时间: fmtDate,
            备注: values.remarks,
            录入者: values.recorder,
          };

          //添加收入
          if (this.data.length == 0) {
            this.data.push(showDatas);
          } else {
            this.data.splice(0, 0, showDatas);
          }

          this.keyCount++;
          this.$store.commit("SET_INCOME_DATAS", this.data);
          callback(0);
        } else {
          //添加失败
          console.log(res);
          alert("录入失败");
          callback(1);
        }
      });
    },
  },
};
</script>


<style scoped>
.editable-row-operations a {
  margin-right: 8px;
}

.advanced-search-form {
  padding: 12px;
  border: 1px solid #d9d9d9;
  border-radius: 6px;
  margin: 15px;
}

.advanced-search-form .ant-form-item {
  display: flex;
}

.advanced-search-form .ant-form-item-control-wrapper {
  flex: 1;
}

#component-advanced-search .ant-form {
  max-width: none;
}

#search-layout-1 {
  text-align: right;
}

.add-layout {
  text-align: left;
  margin: 15px;
}
</style>
  