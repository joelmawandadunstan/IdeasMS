<template>
  <CRow>
    <CCol col="12" xl="8">
      <CCard>
        <CCardHeader> Users </CCardHeader>
        <CCardBody>
          <CDataTable
            hover
            striped
            :items="users"
            :fields="fields"
            :items-per-page="5"
            clickable-rows
            :pagination="{ doubleArrows: false, align: 'center' }"
          >
            <template #Actions="{ item }">
              <td class="py-2">
                <CButton
                  color="info"
                  square
                  size="sm"
                  @click="
                    {
                      warningModal = true;
                      propagateUser(item);
                    }
                  "
                >
                  Edit
                </CButton>&#160;
                <CButton
                  color="danger"
                  square
                  size="sm"
                  @click="deleteUser(item)"
                >
                  Delete
                </CButton>
                <!-- <CBadge :color="getBadge(data.item.status)">
                  {{ data.item.status }}
                </CBadge> -->
              </td>
            </template>
          </CDataTable>
          <div>
            <CModal
              title="Update User"
              color="success"
              :show.sync="warningModal"
            >
              <CInput
                v-model="updateForm.username"
                label="Username"
                placeholder="username..."
                type="text"
                autocomplete="username"
              />
              <CInput
                v-model="updateForm.email"
                placeholder="email"
                type="text"
                autocomplete="email"
              >
                <template #prepend-content><CIcon name="cil-user" /></template>
              </CInput>

              <CRow>
                <CCol sm="4">
                  <CSelect
                    label="Gender"
                    :options="gender"
                    :value.sync="updateForm.genderId"
                  />
                </CCol>

                <CCol sm="4">
                  <CSelect
                    label="Prefix"
                    :options="prefix"
                    :value.sync="updateForm.prefixId"
                  />
                </CCol>
                <CCol sm="4">
                  <CSelect
                    label="Postfix"
                    :options="postfix"
                    :value.sync="updateForm.postfixId"
                  />
                </CCol>
              </CRow>
              <CInput v-model="updateForm.id" placeholder="id" type="hidden" />
              <template #footer>
                <CButton type="submit" color="success">Cancel</CButton>
                <CButton type="submit" color="success" @click="updateUser"
                  >Edit</CButton
                >
              </template>
            </CModal>
          </div>
        </CCardBody>
      </CCard>
    </CCol>
  </CRow>
</template>

<script>
import usersData from "./UsersData";
import axios from "axios";
export default {
  name: "Users",
  components: {},
  data() {
    return {
      users: [],
      gender: [],
      prefix: [],
      postfix: [],
      fields: [
        "id",
        "email",
        "username",
        "role",
        { key: "Actions" },
      ],
      warningModal: false,
      updateForm: {
        id: "",
        email: "",
        username:"",
        genderId: "",
        prefixId: "",
        postfixId: "",
      },
      
    };
  },
  /*  watch: {
    $route: {
      immediate: true,
      handler (route) {
        if (route.query && route.query.page) {
          this.activePage = Number(route.query.page)
        }
      }
    }
  }, */
  methods: {
    deleteUser(item) {
      let deletingUser = item.id;
      console.log(deletingUser);

      axios
        .delete("/api/v1/users/delete/" + deletingUser)
        .then((response) => {
          // Event.fire("updated");
        })
        .catch((error) => {
          console.error(error);
        });
    },
    propagateUser(item) {
      this.updateForm.email = item.email;
      this.updateForm.username = item.username;
      this.updateForm.id = item.id;
      this.updateForm.genderId = item.genderId;
      this.updateForm.prefixId = item.prefixId;
      this.updateForm.postfixId = item.postfixId;
    },
    updateUser() {
      axios
        .patch("/api/v1/users/edit/" + this.updateForm.id, this.updateForm)

        .then((response) => {
          // Event.fire("updated");
        })
        .catch((error) => {
          console.error(error);
        });
    },
    getLookUps() {
      const reqOne = axios.get("/api/v1/gender");
      const reqTwo = axios.get("/api/v1/prefix");
      const reqThree = axios.get("/api/v1/postfix");

      axios.all([reqOne, reqTwo, reqThree]).then(
        axios.spread((gender, prefix, postfix) => {
          this.gender = gender.data.map((singleGender) => {
            return {
              value: singleGender.id,
              label: singleGender.genderName,
            };
          });
          this.prefix = prefix.data.map((singlePrefix) => {
            return {
              value: singlePrefix.id,
              label: singlePrefix.prefixName,
            };
          });
          this.postfix = postfix.data.map((singlePostfix) => {
            return {
              value: singlePostfix.id,
              label: singlePostfix.postfixName,
            };
          });
        })
      );
    },
  },
  mounted() {
    axios.get("/api/v1/users", {
      headers: {
        //"Content-Type": "multipart/form-data",
        "content-type": "application/json",
        Authorization: `Bearer ${localStorage.getItem("accessToken")}`,
      },
    })
    .then((response) => {
      this.users = response.data;
      console.log(this.users);
    })
    .catch((error) => console.log(error));
    
  },
  created() {
    this.getLookUps();
  },
};
</script>
