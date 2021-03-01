
<template>
  <div class="root">
    <div>
      <Head logo="个人理财管理系统" class="head"></Head>
    </div>
    <div style="padding-top: 35px">
      <a-row>
        <a-col :span="24" type="flex" align="middle" justify="center">
          <div class="login-container">
            <a-form id="components-form-login" :form="form" class="login-form">
              <div class="panel-heading">
                <h4 class="title">
                  登录<strong class="text-blue">PPMS</strong>
                </h4>
              </div>
              <a-form-item>
                <a-input
                  class="row-l2"
                  size="large"
                  id="email"
                  type="email"
                  placeholder="邮箱"
                >
                </a-input>
              </a-form-item>
              <a-form-item>
                <a-input
                  class="row-l2"
                  id="password"
                  size="large"
                  type="password"
                  placeholder="密码"
                ></a-input>
              </a-form-item>
              <a-form-item>
                <a-checkbox size="large" style="width: 345px; text-align: left">
                  保持登录状态
                </a-checkbox>
              </a-form-item>
              <a-form-item>
                <a-button
                  class="submit"
                  size="large"
                  type="primary"
                  @click="onSubmit"
                  >登录</a-button
                >
              </a-form-item>
              <a-form-item>
                <div style="width: 345px; text-align: left">
                  <a class="login-form-forgot" href="">忘记密码</a>
                </div>
              </a-form-item>
            </a-form>
            <div class="reg-label">
              <div>
                <p>
                  还没有账号?
                  <router-link to="/reg" class="text-blue-normal"
                    ><b>注册</b></router-link
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
      form: this.$form.createForm(this, { name: "login" }),
    };
  },
  name: "Login",
  components: {
    Head,
  },
};
</script>

<style>
.root {
  position: relative;
  width: 100%;
  height: 100%;
  background-color: #4a81d4;
}

.login-container {
  width: 450px;
  height: 384px;
  background-color: #ffffff;
  border-radius: 4px;
}

.panel-heading {
  padding-top: 50px;
  font-size: 17px;
  letter-spacing: 3px;
}

#components-form-login .submit {
  width: 345px;
  height: 39px;
}

#components-form-login .row-l2 {
  width: 345px;
}

#components-form-login .ant-row {
  margin-bottom: 16px;
}

.text-blue {
  font-size: 20px;
  color: #4a81d4;
}

.text-blue-normal {
  color: #4a81d4;
}

.reg-label {
  position: absolute;
  width: 450px;
  bottom: 0px;
}

.head {
  height: 57px;
  width: 100%;
  background-color: #f2f5f7;
  font-size: 18px;
}
</style>
