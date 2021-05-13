<template>

  <div class="toast-container alert" v-if="GStore.toastMessage"
      :class="{'alert-success': GStore.toastMessage.type ==='success', 'alert-danger': GStore.toastMessage.type ==='danger' }"
  
  >
    <h4>{{GStore.toastMessage.title}}</h4>
    {{GStore.toastMessage.message}}
  </div>
  <nav class="navbar navbar-light bg-light">
    <div class="container-fluid">
      <router-link to="/" class="navbar-brand">Home</router-link>
      <router-link to="/student-list" class="navbar-brand">Student list</router-link>
      <router-link to="/professor-list" class="navbar-brand">Professor list</router-link>
      <router-link to="/subject-list" class="navbar-brand">Subject list</router-link>
      <router-link to="/exam-list" class="navbar-brand">Exam list</router-link>
      <router-link to="/exam-period-list" class="navbar-brand">Exam period list</router-link>
      <!--<router-link to="/exam-registration-list" class="navbar-brand">Exam registration list</router-link>-->
    </div>
  </nav>
  <router-view/>
</template>
<script>
export default {
  inject: ['GStore'],
  beforeUnmount() {
    console.log('Before unmount');
    sessionStorage.removeItem('token');
  }
}
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
</style>
