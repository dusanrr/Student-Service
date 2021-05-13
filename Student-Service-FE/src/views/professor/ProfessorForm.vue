<template>
  <div class="form-container m-4 col-6 col-md-8 col-sm-10 d-flex flex-column">
    <h3 v-if="actionType === 'new'">New Professor</h3>
    <h3 v-else>Edit Professor</h3>

    <MyInputComponent
      readonly
      name="id"
      label="Professor Id"
      v-model="id"
      :vcomp="v$.id"
      v-if="actionType !== 'new'"
    >
    </MyInputComponent>

    <MyInputComponent
      name="firstName"
      label="First name"
      v-model="firstName"
      :vcomp="v$.firstName"
    >
    </MyInputComponent>

    <MyInputComponent
      name="lastName"
      label="Last name"
      v-model="lastName"
      :vcomp="v$.lastName"
    >
    </MyInputComponent>

    <MyInputComponent
      name="email"
      label="Email"
      v-model="email"
      :vcomp="v$.email"
    >
    </MyInputComponent>

    <MyInputComponent
      name="adress"
      label="Adress"
      v-model="adress"
      :vcomp="v$.adress"
    >
    </MyInputComponent>

    <label class="form-label">City</label>
    <div>
      <select class="form-control" v-model="cityDto">
        <option v-for="city in cityList" :value="city" :key="city.number">
          {{ city.name }}
        </option>
      </select>
    </div>

    <MyInputComponent
      name="phone"
      label="Phone"
      v-model="phone"
      :vcomp="v$.phone"
    >
    </MyInputComponent>

    <label class="form-label">Title</label>
    <div>
      <select class="form-control" v-model="titleDto">
        <option v-for="title in titleList" :value="title" :key="title.id">
          {{ title.titleName }}
        </option>
      </select>
    </div>

    <label class="form-label">Reelection date</label>
    <input type="date" v-model="reelectionDate" class="form-control"/>

    <div class="d-flex flex-row-reverse">
      <button class="btn btn-outline-primary" @click="saveProfessor">
        Save
      </button>
    </div>
  </div>
</template>

<script>
import useValidate from "@vuelidate/core";
import {
  required,
  //maxValue,
  minLength,
  maxLength,
  email
} from "@vuelidate/validators";
import MyInputComponent from "@/components/input/MyInputControl.vue";
import ProfessorService from "@/services/ProfessorService.js";
import { addMessage } from "@/main.js";

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
    if (this.professorId) {
      ProfessorService.getProfessor(this.professorId).then((response) => {
        const professor = response.data;
        this.id = professor.id;
        this.firstName = professor.firstName;
        this.lastName = professor.lastName;
        this.email = professor.email;
        this.adress = professor.adress;
        this.cityDto.number = professor.cityDto.number;
        this.cityDto.name = professor.cityDto.name;
        this.phone = professor.phone;
        this.reelectionDate = professor.reelectionDate;
        this.titleDto.id = professor.titleDto.id;
        this.titleDto.titleName = professor.titleDto.titleName;
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
      id: null,
      firstName: "",
      lastName: "",
      email: "",
      adress: "",
      cityDto: {
        number: null,
        name: "",
      },
      phone: "",
      reelectionDate: null,
      titleDto: {
        id: null,
        titleName: "",
      },
    };
  },
  validations() {
    return {
      id: {
        required
      },
      firstName: {
        required,
        minLength: minLength(3),
        maxLength: maxLength(30),
      },
      lastName: {
        required,
        minLength: minLength(3),
        maxLength: maxLength(30),
      },
      email: {
        email
      },
      adress: {
        minLength: minLength(3),
        maxLength: maxLength(50),
      },
      cityDto: {
        //minLength: minLength(3),
        maxLength: maxLength(5),
      },
      phone: {
        minLength: minLength(6),
        maxLength: maxLength(15),
      },
      reelectionDate: {
        required
      },
      titleDto: {
        required,
        //minLength: minLength(1),
        maxLength: maxLength(7),
      }
    };
  },
  methods: {
    saveProfessor() {
      if (this.actionType && this.actionType === "new") {
        this.insertProfessor();
      } else {
        this.updateProfessor();
      }
    },
    insertProfessor() {
      ProfessorService.insertProfessor({
        firstName: this.firstName,
        lastName: this.lastName,
        email: this.email,
        adress: this.adress,
        cityDto: {
          number: this.cityDto.number,
          name: this.cityDto.name,
        },
        phone: this.phone,
        reelectionDate: this.reelectionDate,
        titleDto: {
          id: this.titleDto.id,
          titleName: this.titleDto.titleName,
        },
      })
        .then((response) => {
          console.log("Professor inserted" + response);
          addMessage({
            message: "Professor inserted",
            type: "success",
            title: "PROFESSOR",
          });
          this.$router.push("/professor-list");
        })
        .catch((error) => {
          addMessage({
            message: "Insert was not successful:" + error,
            type: "danger",
            title: "Insert professor",
          });
          this.$router.push("/professor-list");
        });
    },
    updateProfessor() {
      ProfessorService.editProfessor({
        id: this.id,
        firstName: this.firstName,
        lastName: this.lastName,
        email: this.email,
        adress: this.adress,
        cityDto: {
          number: this.cityDto.number,
          name: this.cityDto.name,
        },
        phone: this.phone,
        reelectionDate: this.reelectionDate,
        titleDto: {
          id: this.titleDto.id,
          titleName: this.titleDto.titleName,
        }
      })
        .then((response) => {
          console.log("Professor updated" + response);
          addMessage({
            message: "Professor updated",
            type: "success",
            title: "PROFESSOR",
          });
          this.$router.push("/professor-list");
        })
        .catch((error) => {
          addMessage({
            message: "Update was not successful:" + error,
            type: "danger",
            title: "Update professor",
          });
          this.$router.push("/professor-list");
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
label {
  text-align: start;
}
</style>