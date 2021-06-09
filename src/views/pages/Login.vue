<template>
  <div class="c-app flex-row align-items-center">
    <CContainer>
      <CRow class="justify-content-center">
        <CCol md="8">
          <CCardGroup>
            <CCard class="p-4">
              <CCardBody>
                <CForm @submit.prevent="handleLogin">
                  <h1>Login</h1>
                  <p class="text-muted">Sign In to your account</p>
                  <CInput
                    v-model="loginDetails.username"
                    placeholder="Username"
                    autocomplete="username email"
                  >
                    <template #prepend-content><CIcon name="cil-user"/></template>
                  </CInput>
                  <CInput
                    v-model="loginDetails.password"
                    placeholder="Password"
                    type="password"
                    autocomplete="curent-password"
                  >
                    <template #prepend-content><CIcon name="cil-lock-locked"/></template>
                  </CInput>
                  <CRow>
                    <CCol col="6" class="text-left">
                      <CButton  type="submit" color="primary" class="px-4">Login</CButton>
                    </CCol>
                    <CCol col="6" class="text-right">
                      <CButton color="link" class="px-0">Forgot password?</CButton>
                      <CButton color="link" class="d-lg-none">Register now!</CButton>
                    </CCol>
                  </CRow>
                </CForm>
              </CCardBody>
            </CCard>
            <CCard
              color="primary"
              text-color="white"
              class="text-center py-5 d-md-down-none"
              body-wrapper
            >
              <CCardBody>
                <h2>Sign up</h2>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                <CButton
                  color="light"
                  variant="outline"
                  size="lg"
                >
                  Register Now!
                </CButton>
              </CCardBody>
            </CCard>
          </CCardGroup>
        </CCol>
      </CRow>
    </CContainer>
  </div>
</template>

<script>
import {mapActions, mapGetters} from "vuex";
import axios from "axios";
export default {
  name: 'Login',
   data() {
    return {
      loginDetails:{ username: "",
      password: "",}
    };
  },
 /*  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    },
  }, */
 /*  created() {
    if (this.loggedIn) {
      this.$router.push("/profile");
    }
  }, */
   methods: {
     ...mapActions(["login"]),
   /*  addUser() {
      axios({
        method: "POST",
        url: "api/v1/auth/signin",
        data: {
          username: this.username,
          password: this.password,
        }
      })
        .then((response) => console.log(response.data))
        .catch((error) => console.log(error));
    }, */
     handleLogin() {
      this.loading = true;
      this.login(this.loginDetails)
      .then(() => {
            if (this.getLogginStatus == true) {
              return this.$router.push("/");
            }
          });
     }
  },
  computed: mapGetters(["getLogginStatus"])
}
</script>
