<template>
  <div>
    <a-modal
      title="新建用户"
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
          <a-form-item label="邮箱">
            <a-input
              v-decorator="[
                'email',
                {
                  rules: [{ required: true, message: '请输入邮箱' }],
                  initialValue: '',
                },
              ]"
            />
          </a-form-item>
          <a-form-item label="用户名">
            <a-input
              v-decorator="[
                'userName',
                {
                  rules: [{ required: true, message: '请输入用户名' }],
                  initialValue: '',
                },
              ]"
            />
          </a-form-item>
          <a-form-item label="密码">
            <a-input
              size="large"
              id="password"
              type="password"
              v-decorator="[
                'password',
                {
                  rules: [
                    { required: true, message: '请输入您的密码' },
                    {
                      validator: validateToNextPassword,
                    },
                  ],
                },
              ]"
            >
            </a-input>
          </a-form-item>
          <a-form-item label="确认密码">
            <a-input
              size="large"
              id="confirm-password"
              type="password"
              v-decorator="[
                'confirmPassword',
                {
                  rules: [
                    { required: true, message: '请确认您的密码' },
                    {
                      validator: comparePassword,
                    },
                  ],
                },
              ]"
              @blur="handleConfirmBlur"
            >
            </a-input>
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
      confirmPassword: false,
      visible: false,
      form: this.$form.createForm(this, { name: "newUser" }),
    };
  },
  methods: {
    handleOk() {
      this.form.validateFields((err, values) => {
        if (!err) {
          values["regDate"] = new Date();
          values["loginDate"] = new Date();

          this.$emit("datas", values, (val) => {
            if (val == 0) {
              // 成功
              this.confirmLoading = true;
              setTimeout(() => {
                this.visible = false;
                this.confirmLoading = false;
                this.form.resetFields();
              }, 2000);
            }
          });
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
    validateToNextPassword(rule, value, callback) {
      const form = this.form;
      if (value && this.confirmPassword) {
        form.validateFields(["confirmPassword"], { force: true });
        console.log("validate to next password");
      }
      callback();
    },
    comparePassword(rule, value, callback) {
      const form = this.form;
      if (value && value != form.getFieldValue("password")) {
        callback("您输入的两次密码不一致");
      }
      callback();
    },
    handleConfirmBlur(e) {
      const value = e.target.value;
      this.confirmPassword = this.confirmPassword || !!value;
    },
  },
};
</script>
    
    <style scoped>
</style>
    