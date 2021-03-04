<template>
  <div class="root">
    <div>
      <Head logo="个人理财管理系统" class="head"></Head>
    </div>
    <div class="reg" style="padding-top: 35px">
      <a-row>
        <a-col :span="24" type="flex" align="middle">
          <div class="reg-container">
            <a-form
              id="components-form-reg"
              :form="form"
              class="reg-form"
              @submit="handleSubmit"
            >
              <div class="panel-heading">
                <h4 class="title">
                  注册<strong class="text-blue">PPMS</strong>
                </h4>
              </div>
              <a-form-item>
                <a-input
                  class="row-l2"
                  size="large"
                  id="email"
                  v-decorator="['email', validatorRules.email]"
                  placeholder="邮箱"
                >
                </a-input>
              </a-form-item>
              <a-form-item>
                <a-input
                  class="row-l2"
                  size="large"
                  id="confirm-email"
                  type="email"
                  v-decorator="['confirmEmail', validatorRules.compareEmail]"
                  placeholder="确认邮箱"
                >
                </a-input>
              </a-form-item>
              <a-form-item>
                <a-input
                  class="row-l2"
                  size="large"
                  id="user-name"
                  type="email"
                  placeholder="用户名"
                >
                </a-input>
              </a-form-item>
              <a-form-item>
                <a-input
                  class="row-l2"
                  size="large"
                  id="password"
                  type="email"
                  placeholder="密码"
                >
                </a-input>
              </a-form-item>
              <a-form-item>
                <a-input
                  class="row-l2"
                  size="large"
                  id="confirm-password"
                  type="email"
                  placeholder="确认密码"
                >
                </a-input>
              </a-form-item>
              <a-form-item>
                <a-button
                  class="button-register"
                  type="default"
                  size="large"
                  htmlType="submit"
                  >注册</a-button
                >
              </a-form-item>
            </a-form>
            <div class="row">
              <div>
                <p>
                  已经有账户？
                  <router-link to="/login" class="text-blue-normal"
                    ><b>登录</b></router-link
                  >
                </p>
              </div>
            </div>
          </div>
        </a-col>
      </a-row>
    </div>
  </div>
</template>
<script>
import Head from "../../components/Head.vue";

export default {
  data() {
    return {
      form: this.$form.createForm(this, { name: "reg" }),
      // 表单验证
      validatorRules: {
        email: {
          //验证规则
          rules: [
            {
              type: "email",
              message: "邮箱格式不正确!",
            },
            {
              required: true,
              message: "请输入您的邮箱!",
            },
          ],
        },
        confirmEmail: {
          //验证规则
          rules: [
            {
              required: true,
              type: "email",
              message: "请输入邮箱",
            },
          ],
        },
      },
    };
  },
  methods: {
    handleSubmit(e) {
      e.preventDefault();
      this.form.validateFields((err, values) => {
        if (!err) {
          console.log("Received values of form: ", values);
        }
      });
    },
    compareEmail(rule, value, callback) {
      console.log("compare email func");
      callback();
    },
  },
  name: "Reg",
  components: {
    Head,
  },
};
</script>
<style scoped>
.root {
  position: relative;
  width: 100%;
  height: 100%;
  background-color: #4a81d4;
}

.reg-container {
  width: 450px;
  height: 530px;
  background-color: #ffffff;
  border-radius: 4px;
  padding-left: 50px;
  padding-right: 50px;
}

.head {
  height: 57px;
  width: 100%;
  background-color: #f2f5f7;
  font-size: 18px;
}

#components-form-reg {
  text-align: left;
}

#components-form-reg .panel-heading {
  padding-top: 50px;
  font-size: 17px;
  letter-spacing: 3px;
  text-align: center;
}

#components-form-reg .button-register {
  width: 345px;
  height: 39px;
  border-width: 1;
  border-color: #4a81d4;
}

#components-form-reg .button-register:hover {
  background: #4a81d4;
  color: #ffffff;
}

#components-form-reg .text-blue-normal {
  color: #4a81d4;
}

.text-blue {
  font-size: 20px;
  color: #4a81d4;
}
</style>