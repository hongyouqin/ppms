
<template>
  <div class="root">
    <div>
      <Head logo="个人理财管理系统" class="head"></Head>
    </div>
    <div style="padding-top: 35px">
      <a-row>
        <a-col :span="24" type="flex" align="middle" justify="center">
          <div class="login-container">
            <a-form
              id="components-form-login"
              :form="form"
              class="login-form"
              @submit="handleSubmit"
            >
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
                  v-decorator="[
                    'email',
                    { rules: [{ required: true, message: '请输入邮箱' }] },
                  ]"
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
                  v-decorator="[
                    'password',
                    { rules: [{ required: true, message: '请输入密码' }] },
                  ]"
                  placeholder="密码"
                ></a-input>
              </a-form-item>
              <a-form-item>
                <a-checkbox size="large" style="width: 345px; text-align: left">
                  保持登录状态
                </a-checkbox>
              </a-form-item>
              <a-form-item style="margin-bottom: 4px">
                <a-button
                  class="submit"
                  size="large"
                  type="primary"
                  htmlType="submit"
                  >登录</a-button
                >
              </a-form-item>
            </a-form>
            <div class="forgot">
              <a class="login-form-forgot" href="">忘记密码</a>
            </div>
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
import { mapActions } from "vuex";

export default {
  data() {
    return {
      form: this.$form.createForm(this, { name: "login" }),
    };
  },
  methods: {
    ...mapActions(["Login"]),
    handleSubmit(e) {
      e.preventDefault();
      const {
        form: { validateFields },
        // $router,
        Login,
      } = this;
      validateFields({ force: true }, (err, values) => {
        if (!err) {
          const loginParams = { ...values };
          console.log(loginParams);
          Login(loginParams)
            .then((res) => {
              console.log(res);
            })
            .catch((err) => {
              console.log(err);
            })
            .finally(() => {
              console.log("login complete");
            });
          // $router.push({ name: "Home", params: { ...values } });
        } else {
          console.log("ERR Received values of form: ", values);
        }
      });
    },
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
  height: 404px;
  background-color: #ffffff;
  border-radius: 4px;
  padding-left: 50px;
  padding-right: 50px;
}

#components-form-login .panel-heading {
  padding-top: 50px;
  font-size: 17px;
  letter-spacing: 3px;
  text-align: center;
}

#components-form-login {
  text-align: left;
}

#components-form-login .submit {
  width: 345px;
  height: 39px;
}

.text-blue {
  font-size: 20px;
  color: #4a81d4;
}

.text-blue-normal {
  color: #4a81d4;
}

.forgot {
  text-align: left;
  margin-top: 16px;
}

.reg-label {
  margin-top: 5px;
}

.head {
  height: 57px;
  width: 100%;
  background-color: #f2f5f7;
  font-size: 18px;
}
</style>
