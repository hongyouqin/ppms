<template>
  <div>
    <a-modal
      title="添加权限"
      :visible="visible"
      :confirm-loading="confirmLoading"
      okText="确定"
      cancelText="取消"
      @ok="handleOk"
      @cancel="handleCancel"
    >
      <div>
        <a-checkbox-group @change="onChange">
          <a-row :gutter="[0, 16]">
            <a-col :span="6" v-for="(val, key) in dataSource" :key="key">
              <a-checkbox :value="key">{{ val }}</a-checkbox>
            </a-col>
          </a-row>
        </a-checkbox-group>
      </div>
    </a-modal>
  </div>
</template>
        
<script>
const dataSource = {
  dashboard: "仪表板",
  sys: "系统管理",
  user: "用户",
  role: "角色",
  privilege: "权限",
};

export default {
  name: "userForm",
  data() {
    return {
      confirmLoading: false,
      visible: false,
      selectedItem: {},
      dataSource: { ...dataSource },
    };
  },
  methods: {
    handleOk() {
      if (!this.objIsEmpty(this.selectedItem)) {
        console.log("seled items:", this.selectedItem);
        this.$emit("datas", this.selectedItem);
        this.confirmLoading = true;
        setTimeout(() => {
          this.visible = false;
          this.confirmLoading = false;
        }, 2000);
      }
    },
    onChange(checkedValue) {
      let items = {};
      for (var index in checkedValue) {
        let checked = checkedValue[index];
        let value = this.dataSource[checked];
        items[checked] = value;
      }
      this.selectedItem = { ...items };
    },
    handleCancel() {
      this.visible = false;
    },
    setVisible(visible) {
      this.visible = visible;
    },
    objIsEmpty(obj) {
      //判断对象是否为空
      var arr = Object.keys(obj);
      if (arr.length == 0) {
        return true;
      } else {
        return false;
      }
    },
  },
};
</script>

<style scoped>
.ant-checkbox-group {
  position: relative;
  width: 100%;
}
</style>
        