<template>
  <div class="expense">
    <a-breadcrumb style="margin: 16px 15px; text-align: left">
      <a-breadcrumb-item>主页</a-breadcrumb-item>
      <a-breadcrumb-item>收支管理</a-breadcrumb-item>
      <a-breadcrumb-item>支出</a-breadcrumb-item>
    </a-breadcrumb>
    <div id="component-advanced-search">
      <a-form class="advanced-search-form" :form="form">
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
      <a-button type="primary">添加</a-button>
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
          '生成时间',
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
    title: "操作者",
    dataIndex: "operation",
    scopedSlots: { customRender: "operation" },
  },
];

const data = [];
for (let i = 0; i < 100; i++) {
  data.push({
    key: i.toString(),
    标题: `买菜${i} `,
    金额: "20",
    支出类型: "吃饭",
    生成时间: "2021-04-03 19:20:34",
    冲动性消费: "否",
    备注: "菜市场买菜fdsafsd买菜f买菜f买菜f买菜发觉螺蛳粉就打算减肥快速的减肥",
    记录者: "qinhy",
  });
}
export default {
  data() {
    this.cacheData = data.map((item) => ({ ...item }));
    return {
      data,
      columns,
      editingKey: "",
      expand: false,
      form: this.$form.createForm(this, { name: "advanced_search" }),
    };
  },
  computed: {
    count() {
      return this.expand ? 11 : 7;
    },
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
  