<template>
  <div>
    <a-modal
      title="新建角色"
      :visible="visible"
      :confirm-loading="confirmLoading"
      okText="确定"
      cancelText="取消"
      @ok="handleOk"
      @cancel="handleCancel"
    >
      <div>
        <a-form
          :form="form"
          :label-col="{ span: 5 }"
          :wrapper-col="{ span: 12 }"
        >
          <a-form-item label="角色名">
            <a-input
              v-decorator="[
                'role',
                {
                  rules: [{ required: true, message: '请输入角色名' }],
                  initialValue: '',
                },
              ]"
            />
          </a-form-item>
          <a-form-item label="权限等级">
            <a-select
              v-decorator="[
                'level',
                {
                  initialValue: 'Option2',
                },
              ]"
            >
              <a-select-option value="Option1"> Option1 </a-select-option>
              <a-select-option value="Option2"> Option2 </a-select-option>
            </a-select>
          </a-form-item>
          <a-form-item label="描述">
            <a-textarea
              :rows="4"
              v-decorator="[
                'desc',
                {
                  rules: [{ required: false, message: '请输入描述内容' }],
                  initialValue: '',
                },
              ]"
            />
          </a-form-item>
        </a-form>
      </div>
    </a-modal>
  </div>
</template>
      
  <script>
export default {
  name: "userForm",
  data() {
    return {
      confirmLoading: false,
      visible: false,
      form: this.$form.createForm(this, { name: "newRole" }),
    };
  },
  methods: {
    handleOk() {
      this.form.validateFields((err, values) => {
        if (!err) {
          this.$emit("datas", values);

          console.log("datas = ", values);

          this.confirmLoading = true;
          setTimeout(() => {
            this.visible = false;
            this.confirmLoading = false;
            this.form.resetFields();
          }, 2000);
        }
      });
    },
    handleCancel() {
      this.visible = false;
      this.form.resetFields();
    },
    setVisible(visible) {
      this.visible = visible;
    },
  },
};
</script>
      
<style scoped>
</style>
      