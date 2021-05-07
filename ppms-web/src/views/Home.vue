<template>
  <div class="home">
    <a-layout id="components-layout-root">
      <a-layout-sider
        v-model="collapsed"
        :inline-collapsed="collapsed"
        :trigger="null"
        :width="240"
        style="background: #fff"
      >
        <div class="logo-box">
          <router-link to="" class="logo">
            <span class="logo-lg" v-if="collapsed === false">
              <span class="logo-lg-text-light">个人财务管理系统</span>
            </span>
            <span class="logo-sm" v-else-if="collapsed === true">
              <span class="logo-sm-text-dark">PPMS</span>
            </span>
          </router-link>
        </div>
        <a-menu
          mode="inline"
          theme="light"
          style="text-align: left"
          :default-selected-keys="['home']"
        >
          <a-menu-item key="home" @click="handleDashboard">
            <a-icon type="home" />
            <span>Dashboard</span>
          </a-menu-item>
          <a-sub-menu key="sys-manage">
            <span slot="title">
              <a-icon type="user" />
              <span>系统管理</span>
            </span>
            <a-menu-item key="user-manage" @click="handleUserManage"
              >用户</a-menu-item
            >
            <a-menu-item key="role-manage" @click="handleRole"
              >角色</a-menu-item
            >
            <a-menu-item key="permission-manage" @click="handlePermission"
              >权限</a-menu-item
            >
          </a-sub-menu>
          <a-sub-menu key="ie-manage">
            <span slot="title"
              ><a-icon type="account-book" />
              <span>收支管理</span>
            </span>
            <a-menu-item key="income-manage" @click="handleIncome"
              >收入</a-menu-item
            >
            <a-menu-item
              key="expenses-manage"
              @click="handleExpenses('expense')"
              >支出</a-menu-item
            >
          </a-sub-menu>
          <a-menu-item key="goods-manage" @click="handleGoods">
            <a-icon type="inbox" />
            <span>物品管理</span>
          </a-menu-item>
          <a-menu-item key="invest" @click="handleInvest">
            <a-icon type="gold" />
            <span>个人投资</span>
          </a-menu-item>
          <a-menu-item key="early-warning" @click="handleEarly">
            <a-icon type="alert" />
            <span>财务预警</span>
          </a-menu-item>
        </a-menu>
      </a-layout-sider>
      <a-layout>
        <a-layout-header
          id="header-layout"
          style="background-color: #3a4149; padding: 0"
        >
          <div class="left-bar">
            <a-icon
              class="trigger"
              :type="collapsed ? 'menu-unfold' : 'menu-fold'"
              @click="triggerFunc"
            />
          </div>
          <div class="right-bar">
            <a-dropdown class="wrap" placement="bottomCenter">
              <div>
                <span>
                  <a-avatar :size="48" icon="user" style="margin-right: 8px" />
                  <span style="margin-right: 5px">Qinhy</span>
                  <a-icon type="down" />
                </span>
              </div>
              <a-menu style="width: 170px; padding-left: 10px" slot="overlay">
                <a-menu-item><a-icon type="user" />个人中心</a-menu-item>
                <a-menu-item><a-icon type="setting" />设置</a-menu-item>
                <a-menu-divider></a-menu-divider>
                <a-menu-item @click="handleLoginOut"
                  ><a-icon type="poweroff" />退出登录</a-menu-item
                >
              </a-menu>
            </a-dropdown>
          </div>
        </a-layout-header>
        <a-layout-content>
          <div>
            <keep-alive>
              <router-view v-if="$route.meta.keepAlive"></router-view>
            </keep-alive>
            <router-view
              v-if="$route.meta.keepAlive == undefined"
            ></router-view>
          </div>
        </a-layout-content>
        <a-layout-footer> PPMS ©2021 Created by qhy </a-layout-footer>
      </a-layout>
    </a-layout>
  </div>
</template>


<script>
import { mapActions } from "vuex";

export default {
  name: "Home",
  components: {},
  data() {
    return {
      collapsed: false,
    };
  },
  created() {
    console.log("created home page");
  },
  methods: {
    ...mapActions(["LoginOut"]),
    triggerFunc: function () {
      this.collapsed = !this.collapsed;
    },
    handleDashboard: function () {
      this.$router.push("dashboard");
    },
    handleIncome: function () {
      this.$router.push("income");
    },
    handleExpenses: function (componentName) {
      this.$router.push(componentName);
    },
    handleUserManage: function () {
      this.$router.push("user");
    },
    handleRole: function () {
      this.$router.push("role");
    },
    handlePermission: function () {
      this.$router.push("privilege");
    },
    handleGoods: function () {
      this.$router.push("item");
    },
    handleInvest: function () {
      this.$router.push("investment");
    },
    handleEarly: function () {
      this.$router.push("early");
    },
    handleLoginOut: function () {
      console.log("loginout");
      this.LoginOut();
      this.$router.push("login");
    },
  },
};
</script>


<style src="../utils/css/home-footer.css">
</style>

<style scoped>
.home {
  display: flex;
  flex-direction: column;
  min-height: 100%;
  justify-content: space-between;
}

#components-layout-root .trigger {
  font-size: 25px;
  line-height: 64px;
  padding: 0 24px;
  cursor: pointer;
  color: #ffffff;
}

#components-layout-root .trigger:hover {
  color: #6f747a;
}

#components-layout-root .logo-box {
  height: 64px;
  background-color: #fff;
}

#components-layout-root .logo {
  font-size: 20px;
  color: #595959;
  line-height: 64px;
}

#header-layout .left-bar {
  float: left;
}

#header-layout .right-bar {
  float: right;
}

#header-layout .right-bar .wrap {
  margin-right: 20px;
  line-height: 64px;
  color: #b0b3b6;
  font-size: 16px;
}
</style>
