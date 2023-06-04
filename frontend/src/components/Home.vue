<template>
  <el-container class="home-container">

    <el-header>
      <!--头部标题-->
      <div>
        <span>后台管理系统</span>
      </div>
    </el-header>

    <!--具体内容-->
    <el-container>

      <el-aside :width="isCollapse ? '64px' : '200px'">
        <div class="toggle-button" v-if="isCollapse" @click="toggleCollapse"><i class="el-icon-s-unfold"></i></div>
        <div class="toggle-button" v-else @click="toggleCollapse"><i class="el-icon-s-fold"></i></div>
        <el-menu
            background-color="#333744"
            text-color="#fff"
            active-text-color="#ffd04b"
            router
            unique-opened
            :collapse="isCollapse"
            :collapse-transition="false">
          <el-menu-item v-for="(item,index) in menuList" :key="index">
            <i class="el-icon-setting"></i>
            <span slot="title" @click="$router.push(item.url)">{{ item.name }}</span>
          </el-menu-item>
          <el-menu-item>
            <i class="el-icon-setting"></i>
            <span slot="title" @click="confirmLogout">退出系统</span>
          </el-menu-item>

        </el-menu>
      </el-aside>

      <el-main>
        <router-view v-if="showChildComponent"/>
      </el-main>
    </el-container>
  </el-container>
</template>
<script>

export default {
  name: "Home",
  data() {
    return {
      //左侧菜单列表
      menuList: [],
      adminMenuList: [
        {
          id: 1,
          name: "操作员管理",
          url: "/operator"
        },
        {
          id: 2,
          name: "资源管理",
          url: "/resource"
        },
        {
          id: 3,
          name: "业务管理",
          url: "/charge"
        },
        {
          id: 4,
          name: "开户",
          url: "/openAccount"
        },
        {
          id: 5,
          name: "系统帮助",
          url: ""
        },
        {
          id: 6,
          name: "查询信息",
          url: ""
        },
      ],
      operatorMenuList: [
        {
          id: 2,
          name: "资源管理",
          url: "/resource"
        },
        {
          id: 3,
          name: "业务管理",
          url: "/charge"
        },
        {
          id: 4,
          name: "开户",
          url: "/openAccount"
        },
        {
          id: 5,
          name: "系统帮助",
          url: ""
        },
        {
          id: 6,
          name: "查询信息",
          url: ""
        },
      ],
      isCollapse: false,
      showChildComponent: false,
      token: "",
      operator: []
    };
  },
  methods: {
    toggleCollapse() {
      this.isCollapse = !this.isCollapse
    },
    initUserInfo() {
      this.token = localStorage.getItem("token")
      this.$axios.get("/operator/info").then(response => {
        if (response.data.code === 200) {
          this.operator = response.data.data
          sessionStorage.setItem("operatorName", this.operator.operatorName)
          sessionStorage.setItem("operatorIsAdmin", this.operator.isAdmin)
          sessionStorage.setItem("operatorId", this.operator.operatorId)
          if (this.operator.isAdmin === 'Y') {
            this.menuList = this.adminMenuList
          } else {
            this.menuList = this.operatorMenuList
          }
          this.$nextTick(() => {
            this.showChildComponent = true;
          })
        }
      })
    },
    confirmLogout() {
      this.$confirm('确定要退出登录吗', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.logout()
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '取消退出'
        })
      })
    },
    logout() {
      this.$axios.get("/operator/logout").then(response => {
        if (response.data.code === 200) {
          this.$message.success("退出成功")
          this.$router.push("/login")
          localStorage.clear()
          sessionStorage.clear()
        }
      })
    }
  },
  beforeCreate() {
    document.querySelector('body').setAttribute('style', 'margin:0;padding:0;')
  },
  created() {
    this.initUserInfo()
  }
}
</script>

<style lang="less" scoped>
img {
  width: 30px;
  height: 30px;
}

.toggle-button {
  background-color: #373d48;
  font-size: 20px;
  text-align: center;
  color: #fff;
  letter-spacing: 0.2em;
  cursor: pointer;
}

.el-header {
  background-color: #373d41;
  display: flex;
  justify-content: space-between;
  padding-left: 0;
  align-items: center;
  color: #fff;
  font-size: 22px;

  > div {
    display: flex;
    align-items: center;

    span {
      margin-left: 14px;
    }
  }
}

.el-aside {
  background-color: #333744;

  .el-menu {
    border-right: 0;
  }
}

.el-main {
  background-color: #eaedf1;
}

.home-container {
  height: 100vh;
}
</style>