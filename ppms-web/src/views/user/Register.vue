<template>
  <div class="root">
    <div>
      <Head logo="个人理财管理系统" class="head"></Head>
    </div>
    <div class="reg" style="padding-top: 35px">
      <a-row>
        <a-col :span="24" type="flex" align="middle" justify="center">
          <div class="reg-container">
            <a-form id="components-form-reg" :form="form" class="reg-form">
              <div class="panel-heading">
                <h4 class="title">
                  注册<strong class="text-blue">PPMS</strong>
                </h4>
              </div>
              <a-from-item>
                <a-input
                  class="row-l2"
                  id="email"
                  type="email"
                  placeholder="邮箱"
                >
                </a-input>
              </a-from-item>
              <a-from-item>
                <a-input
                  class="row-l2"
                  id="confirm-email"
                  type="email"
                  placeholder="确认邮箱"
                >
                </a-input>
              </a-from-item>
              <a-from-item>
                <a-input
                  class="row-l2"
                  id="user-name"
                  type="email"
                  placeholder="用户名"
                >
                </a-input>
              </a-from-item>
              <a-from-item>
                <a-input
                  class="row-l2"
                  id="password"
                  type="email"
                  placeholder="密码"
                >
                </a-input>
              </a-from-item>
              <a-from-item>
                <a-input
                  class="row-l2"
                  id="confirm-password"
                  type="email"
                  placeholder="确认密码"
                >
                </a-input>
              </a-from-item>
              <a-from-item>
                <a-button
                  class="button-register"
                  type="default"
                  @click="onSubmit"
                  >注册</a-button
                >
              </a-from-item>
            </a-form>
            <div
              class="row"
              style=" position: absolute width: 450px bottom: 20px"
            >
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

const formItemLayout = {
  labelCol: { span: 4 },
  wrapperCol: { span: 8 },
};

export default {
  data() {
    return {
      formItemLayout,
      form: this.$form.createForm(this, { name: "reg" }),
    };
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
  height: 480px;
  background-color: #ffffff;
  border-radius: 4px;
}

.row {
  position: absolute;
  width: 450px;
  bottom: 20px;
}

.panel-heading {
  padding-top: 50px;
  font-size: 17px;
  letter-spacing: 3px;
}

.head {
  height: 57px;
  width: 100%;
  background-color: #f2f5f7;
  font-size: 18px;
}

#components-form-reg .row-l2 {
  width: 345px;
  height: 39px;
  margin-bottom: 16px;
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