import { createApp, reactive } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import "bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";
import axios from "axios";
import VueAxios from "vue-axios";
import { BootstrapIconsPlugin } from "bootstrap-icons-vue";

const GStore = reactive({
    toastMessage: null
});

createApp(App)
  .use(store)
  .use(router)
  .use(VueAxios, axios)
  .use(BootstrapIconsPlugin)
  .provide('GStore', GStore)
  .mount("#app");


    export function addMessage(messageToDisplay) {
        console.log('Dodaju se poruke.')
        GStore.toastMessage = messageToDisplay;
        setTimeout(() => {
            GStore.toastMessage = ""
        }, 3000)
    }

    export const apiClient = axios.create({
        baseURL: 'http://localhost:8080/'        
    })

    apiClient.interceptors.request.use(
        config => {
            const token = sessionStorage.getItem('token');
            if (token) {
                config.headers.common['Authorization'] = token;
            } 
            return config;
        });