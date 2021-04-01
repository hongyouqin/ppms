<template>
  <div class="home">
    <template>
      <a-layout id="components-layout-root">
        <a-layout-sider
          v-model="collapsed"
          :inline-collapsed="collapsed"
          :trigger="null"
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
          <a-menu mode="inline" theme="light" style="text-align: left">
            <a-menu-item key="home">
              <a-icon type="home" />
              <span>主页</span>
            </a-menu-item>
            <a-sub-menu key="sys-manage">
              <span slot="title">
                <a-icon type="user" />
                <span>系统管理</span>
              </span>
              <a-menu-item key="user-manage">用户</a-menu-item>
              <a-menu-item key="role-manage">角色</a-menu-item>
              <a-menu-item key="permission-manage">权限</a-menu-item>
            </a-sub-menu>
            <a-sub-menu key="ie-manage">
              <span slot="title"
                ><a-icon type="account-book" />
                <span>收支管理</span>
              </span>
              <a-menu-item key="income-manage">收入</a-menu-item>
              <a-menu-item key="expenses-manage">支出</a-menu-item>
            </a-sub-menu>
            <a-menu-item key="goods-manage">
              <a-icon type="inbox" />
              <span>物品管理</span>
            </a-menu-item>
            <a-menu-item key="invest">
              <a-icon type="gold" />
              <span>个人投资</span>
            </a-menu-item>
            <a-menu-item key="early-warning">
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
                    <a-avatar
                      :size="48"
                      icon="user"
                      style="margin-right: 8px"
                    />
                    <span>Qinhy</span>
                    <a-icon type="down" />
                  </span>
                </div>
                <a-menu style="width: 170px; padding-left: 10px" slot="overlay">
                  <a-menu-item><a-icon type="user" />个人中心</a-menu-item>
                  <a-menu-item><a-icon type="setting" />设置</a-menu-item>
                  <a-menu-divider></a-menu-divider>
                  <a-menu-item><a-icon type="poweroff" />退出登录</a-menu-item>
                </a-menu>
              </a-dropdown>
            </div>
          </a-layout-header>
          <a-layout-content>
            <div>Content</div>
          </a-layout-content>
          <a-layout-footer style="text-align: center">
            PPMS ©2021 Created by qhy
          </a-layout-footer>
        </a-layout>
      </a-layout>
    </template>
  </div>
</template>

<script>
export default {
  name: "Home",
  components: {},
  data() {
    return {
      collapsed: false,
    };
  },
  methods: {
    triggerFunc: function () {
      this.collapsed = !this.collapsed;
    },
  },
};
</script>

<style scoped>
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
  background-color: #3a4149;
}

#components-layout-root .logo {
  font-size: 20px;
  color: #ffffff;
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
