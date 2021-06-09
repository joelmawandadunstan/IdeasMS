<template>
  <div class="d-flex align-items-center min-vh-100">
    <CContainer fluid>
      <CRow class="justify-content-center">
        <CCol md="6">
          <CCard class="mx-4 mb-0">
            <CCardBody class="p-4">
              <CForm @submit.prevent="addUser">
                <h1>Register</h1>
                <p class="text-muted">Create your account</p>
                <CInput
                  v-model="username"
                  placeholder="User Name"
                  type="text"
                  autocomplete="username"
                >
                  <template #prepend-content
                    ><CIcon name="cil-user"
                  /></template>
                </CInput>

                <CInput
                  v-model="email"
                  placeholder="Email"
                  type="text"
                  autocomplete="email"
                  prepend="@"
                />
                <CRow>
                  <CCol sm="4">
                    <CSelect label="Gender" 
                    :options="gender"
                    :value.sync="genderId" />
                  </CCol>

                  <CCol sm="4">
                    <CSelect label="Prefix" 
                    :options="prefix"
                    :value.sync="prefix_id" />
                  </CCol>

                  <CCol sm="4">
                    <CSelect label="Postfix" 
                    :options="postfix" 
                    :value.sync="postfix_id"/>
                  </CCol>
                </CRow>

                <CInput
                  v-model="password"
                  placeholder="Password"
                  type="password"
                  autocomplete="new-password"
                >
                  <template #prepend-content
                    ><CIcon name="cil-lock-locked"
                  /></template>
                </CInput>
                <!-- <CInput
                  placeholder="Repeat password"
                  type="password"
                  autocomplete="new-password"
                  class="mb-4"
                >
                  <template #prepend-content><CIcon name="cil-lock-locked"/></template>
                </CInput> -->
                <CButton type="submit" color="success" block>Sign Up</CButton>
              </CForm>
            </CCardBody>
            <CCardFooter class="p-4">
              <CRow>
                <CCol col="6">
                  <CButton block color="facebook"> Facebook </CButton>
                </CCol>
                <CCol col="6">
                  <CButton block color="twitter"> Twitter </CButton>
                </CCol>
              </CRow>
            </CCardFooter>
          </CCard>
        </CCol>
      </CRow>
    </CContainer>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "Register",
  data() {
    return {
      username: "",
      email: "",
      password: "",
      genderId: '',
      prefixId: '',
      postfixId: '',
      gender: [],
      prefix: [],
      postfix: [],
    };
  },
  methods: {
    getLookUps() {
      // const req1 = "api/v1/gender"
      // const req2 = "api/v1/prefix"
      // const req3 = "api/v1/postfix"

      const reqOne = axios.get("/api/v1/gender");
      const reqTwo = axios.get("/api/v1/prefix");
      const reqThree = axios.get("/api/v1/postfix");

      axios.all([reqOne, reqTwo, reqThree]).then(
        axios.spread((gender, prefix, postfix) => {
          this.gender = (gender.data).map((singleGender) => {
            return {
              value: singleGender.id,
              label: singleGender.genderName,
            };
          });
           this.prefix = (prefix.data).map((singlePrefix) => {
            return {
              value: singlePrefix.id,
              label: singlePrefix.prefixName,
            };
          });

          this.postfix = (postfix.data).map((singlePostfix) => {
            return {
              value: singlePostfix.id,
              label: singlePostfix.postfixName,
            };
          });
        })
      );
    },

    addUser() {
      // console.table(
      //     {firstName: this.firstName,
      //     lastName: this.lastName,
      //     email: this.email,
      //     genderId: this.gender_id,
      //     prefixId: this.prefix_id,
      //     postfixId: this.postfix_id}
      // )
      axios({
        method: "POST",
        url: "api/v1/auth/signup",
        data: {
          username: this.username,
          email: this.email,
          genderId: this.genderId,
          prefixId: this.prefixId,
          postfixId: this.postfixId,
          password: this.password,
        }
      })
        .then((response) => console.log(response.data))
        .catch((error) => console.log(error));
    },
  },
  created() {
    this.getLookUps();
  },
};
</script>
