<template>
  <div v-if="GStore.toastMessage"></div>

  <nav
    class="
      navbar navbar-icon-top navbar-expand-lg navbar-dark
      bg-dark
      position-sticky
      fixed-top
    "
  >
    <a class="navbar-brand" href="#" style="margin-left: 20px"
      >STUDENT SERVICE</a
    >
    <button
      class="navbar-toggler"
      type="button"
      data-toggle="collapse"
      data-target="#navbarSupportedContent"
      aria-controls="navbarSupportedContent"
      aria-expanded="false"
      aria-label="Toggle navigation"
    >
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav mr-auto">
        <li
          class="nav-item"
          v-if="showAdminBoard || showModeratorBoard || showUserBoard"
        >
          <router-link to="/home" class="nav-link"
            ><i class="fa fa-home"></i>Home</router-link
          >
        </li>
        <li class="nav-item" v-if="showAdminBoard">
          <router-link to="/student-list" class="nav-link"
            ><i class="fa fa-graduation-cap"></i>Students</router-link
          >
        </li>
        <li class="nav-item" v-if="showAdminBoard">
          <router-link to="/professor-list" class="nav-link"
            ><i class="fa fa-users"></i>Professors</router-link
          >
        </li>
        <li class="nav-item" v-if="showAdminBoard">
          <router-link to="/subject-list" class="nav-link"
            ><i class="fa fa-book"></i>Subjects</router-link
          >
        </li>
        <li class="nav-item" v-if="showAdminBoard">
          <router-link to="/exam-list" class="nav-link"
            ><i class="fa fa-etsy"></i>Exams</router-link
          >
        </li>
        <li class="nav-item" v-if="showAdminBoard">
          <router-link to="/exam-period-list" class="nav-link"
            ><i class="fa fa-calendar-check-o"></i>Exam periods</router-link
          >
        </li>
        <li class="nav-item" v-if="showAdminBoard">
          <router-link to="/exam-registration-list" class="nav-link"
            ><i class="fa fa-registered"></i>Exam registrations</router-link
          >
        </li>
      </ul>

      <ul
        class="nav pull-right"
        style="position: absolute; right: 0; margin-right: 25px"
      >
        <li>
          <div id="user_nav" v-if="!currentUser">
            <router-link
              to="/register"
              class="btn-mini btn btn-outline-warning"
              style="margin-right: 5px"
              ><i class="fa fa-user"></i> Sign Up</router-link
            >
            <router-link to="/login" class="btn btn-outline-warning btn-mini"
              ><i class="fa fa-sign-in"></i> Login</router-link
            >
          </div>

          <div id="user_nav" v-if="currentUser">
            <router-link
              to="/profile"
              class="btn-mini btn btn-outline-warning"
              style="margin-right: 5px"
              ><i class="fa fa-user"></i>
              {{ currentUser.username }}</router-link
            >
            <a class="nav-link" @click.prevent="logOut"
              ><i class="fa fa-sign-out"></i> LogOut
            </a>
          </div>
        </li>
      </ul>
    </div>
  </nav>
  <router-view />

  <footer class="container position-relative">
    <hr class="featurette-divider" />
    <p class="float-right"><a href="#">Back to the top</a></p>
    <p>&copy; 2021 Dusan Rajkovic</p>
  </footer>
</template>
<script>

export default {
  inject: ["GStore"],
  updated() {
    if (this.GStore.toastMessage) {
      this.$toast.show(this.GStore.toastMessage.message, {
        type: "error",
        duration: 6000,
      });
    }
  },
  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    },
    currentUser() {
      return this.$store.state.auth.user;
    },
    showAdminBoard() {
      if (this.currentUser && this.currentUser["roles"]) {
        return this.currentUser["roles"].includes("ROLE_ADMIN");
      }

      return false;
    },
    showModeratorBoard() {
      if (this.currentUser && this.currentUser["roles"]) {
        return this.currentUser["roles"].includes("ROLE_MODERATOR");
      }

      return false;
    },
    showUserBoard() {
      if (this.currentUser && this.currentUser["roles"]) {
        return this.currentUser["roles"].includes("ROLE_USER");
      }

      return false;
    },
  },
  created() {
    if (!this.loggedIn) return this.$router.push("/login");
  },
  methods: {
    logOut() {
      this.$store.dispatch("auth/logout");
      this.$router.push("/login");
    },
  },
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}

.toast-container {
  position: fixed !important;
  top: 5px;
  right: 5px;
  min-width: 400px;
  text-align: start;
}

.form-container {
  border-radius: 10px;
  border: 1pt solid grey;
  padding: 10px;
}

.position-relative {
  top: 220px;
}

@import url("//maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css");

.navbar-icon-top .navbar-nav .nav-link > .fa {
  position: relative;
  width: 36px;
  font-size: 24px;
}

.navbar-icon-top .navbar-nav .nav-link > .fa > .badge {
  font-size: 0.75rem;
  position: absolute;
  right: 0;
  font-family: sans-serif;
}

.navbar-icon-top .navbar-nav .nav-link > .fa {
  top: 3px;
  line-height: 12px;
}

.navbar-icon-top .navbar-nav .nav-link > .fa > .badge {
  top: -10px;
}

@media (min-width: 576px) {
  .navbar-icon-top.navbar-expand-sm .navbar-nav .nav-link {
    text-align: center;
    display: table-cell;
    height: 70px;
    vertical-align: middle;
    padding-top: 0;
    padding-bottom: 0;
  }

  .navbar-icon-top.navbar-expand-sm .navbar-nav .nav-link > .fa {
    display: block;
    width: 48px;
    margin: 2px auto 4px auto;
    top: 0;
    line-height: 24px;
  }

  .navbar-icon-top.navbar-expand-sm .navbar-nav .nav-link > .fa > .badge {
    top: -7px;
  }
}

@media (min-width: 768px) {
  .navbar-icon-top.navbar-expand-md .navbar-nav .nav-link {
    text-align: center;
    display: table-cell;
    height: 70px;
    vertical-align: middle;
    padding-top: 0;
    padding-bottom: 0;
  }

  .navbar-icon-top.navbar-expand-md .navbar-nav .nav-link > .fa {
    display: block;
    width: 48px;
    margin: 2px auto 4px auto;
    top: 0;
    line-height: 24px;
  }

  .navbar-icon-top.navbar-expand-md .navbar-nav .nav-link > .fa > .badge {
    top: -7px;
  }
}

@media (min-width: 992px) {
  .navbar-icon-top.navbar-expand-lg .navbar-nav .nav-link {
    text-align: center;
    display: table-cell;
    height: 70px;
    vertical-align: middle;
    padding-top: 0;
    padding-bottom: 0;
  }

  .navbar-icon-top.navbar-expand-lg .navbar-nav .nav-link > .fa {
    display: block;
    width: 48px;
    margin: 2px auto 4px auto;
    top: 0;
    line-height: 24px;
  }

  .navbar-icon-top.navbar-expand-lg .navbar-nav .nav-link > .fa > .badge {
    top: -7px;
  }
}

@media (min-width: 1200px) {
  .navbar-icon-top.navbar-expand-xl .navbar-nav .nav-link {
    text-align: center;
    display: table-cell;
    height: 70px;
    vertical-align: middle;
    padding-top: 0;
    padding-bottom: 0;
  }

  .navbar-icon-top.navbar-expand-xl .navbar-nav .nav-link > .fa {
    display: block;
    width: 48px;
    margin: 2px auto 4px auto;
    top: 0;
    line-height: 24px;
  }

  .navbar-icon-top.navbar-expand-xl .navbar-nav .nav-link > .fa > .badge {
    top: -7px;
  }
}

.required-field::before {
  content: "* ";
  color: red;
}
</style>
