<template>
  <div class="expense">
    <a-breadcrumb style="margin: 16px 15px; text-align: left">
      <a-breadcrumb-item>主页</a-breadcrumb-item>
      <a-breadcrumb-item>收支管理</a-breadcrumb-item>
      <a-breadcrumb-item>支出</a-breadcrumb-item>
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
            <a-form-item :label="`记录名称`">
              <a-input placeholder="请输入名字" />
            </a-form-item>
          </a-col>
          <a-col :span="8">
            <a-form-item :label="`支出类型`">
              <a-input placeholder="请输入分类名" />
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
      <expense-form ref="expense" @datas="eventDatas"></expense-form>
    </div>
    <a-table :columns="columns" :data-source="data">
      <template
        v-for="col in [
          '标题',
          '金额',
          '支出类型',
          '冲动性消费',
          '备注',
          '记录者',
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
import expenseForm from "../components/ExpenseForm.vue";
import { formatDatetime } from "../utils/dateUtil.js";

const columns = [
  {
    title: "标题",
    dataIndex: "标题",
    scopedSlots: { customRender: "标题" },
  },
  {
    title: "金额",
    dataIndex: "金额",
    scopedSlots: { customRender: "金额" },
  },
  {
    title: "支出类型",
    dataIndex: "支出类型",
    scopedSlots: { customRender: "支出类型" },
  },
  {
    title: "冲动性消费",
    dataIndex: "冲动性消费",
    scopedSlots: { customRender: "冲动性消费" },
  },
  {
    title: "备注",
    dataIndex: "备注",
    scopedSlots: { customRender: "备注" },
  },
  {
    title: "记录者",
    dataIndex: "记录者",
    scopedSlots: { customRender: "记录者" },
  },
  {
    title: "生成时间",
    dataIndex: "生成时间",
    scopedSlots: { customRender: "生成时间" },
  },
  {
    title: "操作",
    dataIndex: "operation",
    scopedSlots: { customRender: "operation" },
  },
];

var data = [];
// for (let i = 0; i < 100; i++) {
//   data.push({
//     key: i.toString(),
//     标题: `买菜${i} `,
//     金额: "20",
//     支出类型: "吃饭",
//     生成时间: "2021-04-03 19:20:34",
//     冲动性消费: "否",
//     备注: "菜市场买菜fdsafsd买菜f买菜f买菜f买菜发觉螺蛳粉就打算减肥快速的减肥",
//     记录者: "qinhy",
//   });
// }

export default {
  data() {
    this.cacheData = data.map((item) => ({ ...item }));
    return {
      data,
      columns,
      editingKey: "",
      keyCount: 0,
      form: this.$form.createForm(this, { name: "advanced_search" }),
    };
  },
  created() {
    console.log("created expense page");
    let datas = this.$store.getters.getExpenseDatas;
    console.log("created datas: ", [...datas]);
    this.data = datas;
    this.keyCount = this.data.length;
    this.cacheData = this.data.map((item) => ({ ...item }));
  },
  computed: {},
  name: "Expense",
  components: {
    expenseForm,
  },
  methods: {
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
      console.log("target", target);
      console.log("target cache", targetCache);
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
      this.$refs.expense.setVisible(true);
    },
    eventDatas(values) {
      var date = values.date;
      let fmtDate = formatDatetime(date, "yyyy-MM-dd hh:mm:sss");
      var showDatas = {
        key: this.keyCount.toString(),
        标题: values.title,
        金额: values.price,
        支出类型: values.expensesType,
        生成时间: fmtDate,
        冲动性消费: values.impulse,
        备注: values.remarks,
        记录者: values.recorder,
      };

      //添加支出
      if (this.data.length == 0) {
        this.data.push(showDatas);
      } else {
        this.data.splice(0, 0, showDatas);
      }

      this.keyCount++;
      this.$store.commit("SET_EXPENSE_DATAS", this.data);
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
  