import { createApp, reactive } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import "jquery";
import "bootstrap/dist/js/bootstrap.min.js";
import "bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";
import axios from "axios";
import VueAxios from "vue-axios";
import { BootstrapIconsPlugin } from "bootstrap-icons-vue";
import VueLoading from 'vue-loading-overlay';
import 'vue-loading-overlay/dist/vue-loading.css';
import Toaster from "@meforma/vue-toaster";
import { FontAwesomeIcon } from './plugins/font-awesome';


const GStore = reactive({
    toastMessage: null
});

createApp(App)
  .use(store)
  .use(router)
  .use(VueAxios, axios)
  .use(BootstrapIconsPlugin)
  .use(VueLoading)
  .use(Toaster, { position: "bottom" })
  .use("font-awesome-icon", FontAwesomeIcon)
  .provide('GStore', GStore)
  .mount("#app");
  
    export function addMessage(messageToDisplay) {
        console.log('Dodaju se poruke.')
        GStore.toastMessage = messageToDisplay;
        setTimeout(() => {
            GStore.toastMessage = ""
        }, 7000)
    }

    export const apiClient = axios.create({
        baseURL: 'http://localhost:8080/'        
    })

    const self = this;
    //error handling response
    apiClient.interceptors.response.use(
        function(response) { return response;}, 
        function(error) {
            // handle error
            if (error.response) {
                //alert(error.response.data.message);
                addMessage({
                    message: error + " - " + error.response.data,
                    type: "danger",
                    title: "Error",
                  });

                 self.$toast.show("Something went wrong!", {
                    type: "error",
                    duration: 6000
                  });
            }
        });