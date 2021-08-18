<template>
  <div class="container">
    <img alt="Student service" src="../../assets/student-crud.png">
    <div class="row justify-content-center">
      <div class="col-4" style="margin-top: 50px;">
        <h3 v-if="actionType === 'new'">New Professor</h3>
        <h3 v-else>Edit Professor</h3>

        <MyInputComponent name="id" label="Professor Id" v-model="professor.id" :vcomp="v$.professor.id" v-if="actionType !== 'new'" readonly></MyInputComponent>

        <MyInputComponent requiredField="true" name="firstName" label="First name" v-model="professor.firstName" :vcomp="v$.professor.firstName"></MyInputComponent>

        <MyInputComponent requiredField="true" name="lastName" label="Last name" v-model="professor.lastName" :vcomp="v$.professor.lastName"></MyInputComponent>

        <MyInputComponent name="email" label="Email" v-model="professor.email" :vcomp="v$.professor.email"></MyInputComponent>

        <MyInputComponent requiredField="true" name="adress" label="Adress" v-model="professor.adress" :vcomp="v$.professor.adress"></MyInputComponent>

        <div class="label-left">
          <label class="form-label">City</label>
          <div>
            <select class="form-control" v-model="professor.city">
              <option v-for="city in cityList" :value="city" :key="city.number">
                {{ city.name }}
              </option>
            </select>
          </div>
        </div>

        <MyInputComponent name="phone" label="Phone" v-model="professor.phone" :vcomp="v$.professor.phone"></MyInputComponent>

        <div class="label-left">
          <label class="form-label required-field">Title</label>
          <select class="form-control" v-model="professor.title">
            <option v-for="title in titleList" :value="title" :key="title.id">
              {{ title.titleName }}
            </option>
          </select>
        </div>

        <div class="label-left">
          <label class="form-label required-field">Reelection date</label>
          <input type="date" v-model="professor.reelectionDate" class="form-control"/>
        </div>

         <button class="btn btn-outline-primary" @click="saveProfessor" :disabled='!isComplete'>Save</button>
         <button class="btn btn-outline-danger cancel" @click="returnBack">Cancel</button>

      </div>
    </div>
  </div>

</template>

<script>
import useValidate from "@vuelidate/core";
import { required, minLength, maxLength,email } from "@vuelidate/validators";
import MyInputComponent from "@/components/input/MyInputControl.vue";
import ProfessorService from "@/services/ProfessorService.js";

export default {
  components: { MyInputComponent },
  props: {
    professorId: {
      type: String,
      required: true,
    },
    actionType: {
      type: String,
    },
  },
  created() {
    this.vueLoader();
    if (this.professorId) {
      ProfessorService.getProfessor(this.professorId).then((response) => {
        this.professor = response.data;
      });
    }
    this.getAllCities();
    this.getAllTitles();
  },
  data() {
    return {
      v$: useValidate(),
      cityList: [],
      titleList: [],
      professor: {}
    };
  },
  validations() {
    return {
      professor:{
        id: { required },
        firstName: { required, minLength: minLength(3), maxLength: maxLength(30) },
        lastName: { required, minLength: minLength(3), maxLength: maxLength(30) },
        email: { email },
        adress: { minLength: minLength(3), maxLength: maxLength(50) },
        city: { maxLength: maxLength(5) },
        phone: { minLength: minLength(6), maxLength: maxLength(15) },
        reelectionDate: { required },
        title: { required, maxLength: maxLength(7) }
      }
    };
  },
  computed: {
    isComplete () {
      if(this.actionType == 'new')
        return this.professor.firstName && this.professor.lastName && this.professor.reelectionDate && this.professor.title;
        else
          return this.professor.id && this.professor.firstName && this.professor.lastName && this.professor.reelectionDate && this.professor.title;
    }
  },
  methods: {
    returnBack() {
      this.$router.go(-1);
    },
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
    },
    saveProfessor() {
      if (this.actionType && this.actionType === "new") {
        this.insertProfessor();
      } else {
        this.updateProfessor();
      }
    },
    insertProfessor() {
      ProfessorService.insertProfessor(this.professor)
        .then((response) => {
          console.log("Professor inserted" + response.data);

          this.$toast.show("Professor inserted!", {
                    type: "success",
                    duration: 6000
                  });
          this.$router.push("/professor-list");
        })
        .catch((error) => {
          console.log(error);
        });
    },
    updateProfessor() {
      ProfessorService.editProfessor(this.professor)
        .then((response) => {
          console.log("Professor updated" + response.data);

          this.$toast.show("Professor updated!", {
                    type: "success",
                    duration: 6000
                  });
          this.$router.push("/professor-list");
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getAllCities() {
      ProfessorService.getAllCities().then((response) => {
        console.log(response);
        this.cityList = response.data;
      });
    },
    getAllTitles() {
      ProfessorService.getAllTitles().then((response) => {
        console.log(response);
        this.titleList = response.data;
      });
    },
  },
};
</script>

<style scoped>
.label-left
{
  text-align: left;
  padding-bottom: 10px;
}

img{
  height: 200px;
  width: 200px;
  margin-top: 30px;
}

.cancel{
  margin-left: 10px;
}
</style>