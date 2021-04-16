<template>
  <div class="privilege">
    <a-breadcrumb style="margin: 16px 15px; text-align: left">
      <a-breadcrumb-item>主页</a-breadcrumb-item>
      <a-breadcrumb-item>系统管理</a-breadcrumb-item>
      <a-breadcrumb-item>权限</a-breadcrumb-item>
    </a-breadcrumb>
    <div class="add-layout">
      <a-button type="primary" @click="addRole">+ 新建</a-button>
      <privilege-form ref="newRole" @datas="eventDatas"></privilege-form>
    </div>
    <a-table :columns="columns" :data-source="data">
      <template
        v-for="col in [
          listHead.menuName,
          listHead.privilegeLevel,
          listHead.desc,
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
          </span>
          <span v-else>
            <a :disabled="editingKey !== ''" @click="() => edit(record.key)"
              >编辑</a
            >
          </span>

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
import privilegeForm from "../components/privilegeForm";

const dataHeads = {
  menuName: "菜单名",
  privilegeLevel: "权限等级",
  desc: "描述",
  operation: "操作",
};

const columns = [
  {
    title: dataHeads.menuName,
    dataIndex: dataHeads.menuName,
    scopedSlots: { customRender: dataHeads.menuName },
  },
  {
    title: dataHeads.privilegeLevel,
    dataIndex: dataHeads.privilegeLevel,
    scopedSlots: { customRender: dataHeads.privilegeLevel },
  },
  {
    title: dataHeads.desc,
    dataIndex: dataHeads.desc,
    scopedSlots: { customRender: dataHeads.desc },
  },
  {
    title: dataHeads.operation,
    dataIndex: "operation",
    scopedSlots: { customRender: "operation" },
  },
];

var data = [];

export default {
  name: "Privilege",
  data() {
    this.cacheData = data.map((item) => ({ ...item })); //复制一个新的数组
    return {
      data,
      columns,
      editingKey: "",
      keyCount: 0,
      listHead: {
        menuName: dataHeads.menuName,
        privilegeLevel: dataHeads.privilegeLevel,
        desc: dataHeads.desc,
        operation: dataHeads.operation,
      },
    };
  },
  created() {
    console.log("created Privilege page");
  },
  components: {
    privilegeForm,
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
    onDelete(key) {
      const data = [...this.data];
      this.data = data.filter((item) => key !== item.key);
    },
    addRole() {
      this.$refs.newRole.setVisible(true);
    },
    save(key) {
      const newData = [...this.data];
      const newCacheData = [...this.cacheData];
      const target = newData.filter((item) => key === item.key)[0];
      const targetCache = newCacheData.filter((item) => key === item.key)[0];
      console.log(targetCache);
      if (target && targetCache) {
        delete target.editable;
        this.data = newData;
        Object.assign(targetCache, target);
        this.cacheData = newCacheData;
      }
      this.editingKey = "";
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
    equalMenu(menu, datas) {
      for (var i = 0; i < datas.length; ++i) {
        let dstMenu = datas[i].菜单名;
        if (dstMenu.localeCompare(menu) == 0) {
          return true;
        }
      }
      return false;
    },
    eventDatas(values) {
      for (var i in values) {
        let menu = values[i];
        if (this.equalMenu(menu, this.data)) {
          continue;
        }
        var showDatas = {
          key: this.keyCount.toString(),
          菜单名: menu,
          权限等级: 0,
          描述: "",
        };
        if (this.data.length == 0) {
          this.data.push(showDatas);
        } else {
          this.data.splice(0, 0, showDatas);
        }
        this.keyCount++;
        //数据添加到缓存
        this.cacheData = data.map((item) => ({ ...item }));
      }
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
  margin-top: 30px;
}
</style>