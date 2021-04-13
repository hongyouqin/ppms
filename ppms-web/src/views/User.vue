<template>
  <div class="user">
    <a-breadcrumb style="margin: 16px 15px; text-align: left">
      <a-breadcrumb-item>主页</a-breadcrumb-item>
      <a-breadcrumb-item>系统管理</a-breadcrumb-item>
      <a-breadcrumb-item>用户</a-breadcrumb-item>
    </a-breadcrumb>
    <div class="search">
      <a-input-search
        placeholder="请输入账号或者用户名"
        style="width: 400px"
        size="large"
        @search="onSearch"
      />
    </div>
    <div class="add-layout">
      <a-button type="primary" @click="addUser">+ 新建</a-button>
      <user-form ref="newUser" @datas="eventDatas"></user-form>
    </div>
    <a-table :columns="columns" :data-source="data">
      <template
        v-for="col in [
          listHead.userName,
          listHead.email,
          'regTime',
          'loginTime',
        ]"
        :slot="col"
        slot-scope="text"
      >
        <div :key="col">
          <template>
            {{ text }}
          </template>
        </div>
      </template>
      <template slot="operation" slot-scope="text, record">
        <div class="editable-row-operations">
          <span>
            <a-popconfirm
              title="确定删除?"
              @confirm="() => onDelete(record.key)"
            >
              <a>删除</a>
            </a-popconfirm>
          </span>
        </div>
      </template>
    </a-table>
  </div>
</template>

<script>
import userForm from "../components/userForm.vue";
import { formatDatetime } from "../utils/dateUtil.js";

const dataHeads = {
  email: "邮箱",
  userName: "用户名",
  loginTime: "登录时间",
  regTime: "注册时间",
  operation: "操作",
};

const columns = [
  {
    title: dataHeads.userName,
    dataIndex: dataHeads.userName,
    scopedSlots: { customRender: dataHeads.userName },
  },
  {
    title: dataHeads.email,
    dataIndex: dataHeads.email,
    scopedSlots: { customRender: dataHeads.email },
  },
  {
    title: dataHeads.regTime,
    dataIndex: dataHeads.regTime,
    scopedSlots: { customRender: dataHeads.regTime },
  },
  {
    title: dataHeads.loginTime,
    dataIndex: dataHeads.loginTime,
    scopedSlots: { customRender: dataHeads.loginTime },
  },
  {
    title: dataHeads.operation,
    dataIndex: "operation",
    scopedSlots: { customRender: "operation" },
  },
];

var data = [];

export default {
  name: "User",
  data() {
    this.cacheData = data.map((item) => ({ ...item }));
    return {
      data,
      columns,
      keyCount: 0,
      listHead: {
        email: dataHeads.email,
        userName: dataHeads.userName,
        loginTime: dataHeads.loginTime,
        regTime: dataHeads.regTime,
        operation: dataHeads.operation,
      },
    };
  },
  created() {
    console.log("created User page");
    let datas = this.$store.getters.getUserDatas;
    console.log("created datas: ", [...datas]);
    this.data = datas;
    this.keyCount = this.data.length;
    this.cacheData = this.data.map((item) => ({ ...item }));
  },
  components: {
    userForm,
  },
  methods: {
    onSearch(value) {
      console.log(value);
    },
    onDelete(key) {
      const data = [...this.data];
      this.data = data.filter((item) => key !== item.key);
      //保存数据
      this.$store.commit("SET_USER_DATAS", this.data);
    },
    addUser() {
      this.$refs.newUser.setVisible(true);
    },
    eventDatas(values) {
      let regDate = formatDatetime(values.regDate, "yyyy-MM-dd hh:mm:sss");
      let loginDate = formatDatetime(values.loginDate, "yyyy-MM-dd hh:mm:sss");

      var showDatas = {
        key: this.keyCount.toString(),
        邮箱: values.email,
        用户名: values.userName,
        登录时间: loginDate,
        注册时间: regDate,
      };

      //添加收入
      if (this.data.length == 0) {
        this.data.push(showDatas);
      } else {
        this.data.splice(0, 0, showDatas);
      }

      this.keyCount++;
      this.$store.commit("SET_USER_DATAS", this.data);
    },
  },
};
</script>

<style scoped>
.editable-row-operations a {
  margin-right: 8px;
}

.add-layout {
  text-align: left;
  margin: 15px;
}
</style>