<template>
  <div class="home">
    <img alt="Student service" src="../assets/student-srvc.png">
    <HelloWorld msg="Welcome to Student Service"/>
  </div>

</template>

<script>
import HelloWorld from '@/components/HelloWorld.vue'

export default {
  components: { HelloWorld },
  name: 'Home',
  methods: {
      vueLoader()
      {
        
        let loader = this.$loading.show({
        // Optional parameters
        container: this.$refs.loadingContainer,
        color: '#0C937A',
        loader: 'dots',
        width: 64,
        height: 64,
        backgroundColor: '#EFEBEB',
        opacity: 1.0,
        zIndex: 999,
        });
        // simulate AJAX
        setTimeout(() => {
            loader.hide()
        }, 3000)
    }
  },
  created()
  {
      this.vueLoader();
  }
  
}
</script>

<style scoped>
img{
  height: 700px;
  width: 800px;
  padding: 100px;
}
</style>
