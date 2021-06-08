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
            :active-page="activePage"
            @row-clicked="rowClicked"
            :pagination="{ doubleArrows: false, align: 'center' }"
            @page-change="pageChange"
          >
            <template #Actions="{ item }">
              <td class="py-2">
                <CButton
                  color="info"
                  variant="outline"
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
                </CButton>
                <CButton
                  color="danger"
                  variant="outline"
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
                v-model="updateForm.firstName"
                label="firstName"
                placeholder="Content..."
                type="text"
                autocomplete="firstName"
              />
              <CInput
                v-model="updateForm.lastName"
                label="lastName"
                placeholder="Content..."
                type="text"
                autocomplete="lastName"
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
      fields: [
        "id",
        "email",
        "firstName",
        "lastName",
        "role",
        { key: "Actions" },
      ],
      warningModal: false,
      updateForm: {
        id: "",
        email: "",
        firstName: "",
        lastName: "",
        genderId: "",
        prefixId: "",
        postfixId: ""
      },
      // genderId: [],
      // prefixId: [],
      // postfixId: [],
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
      this.updateForm.firstName = item.firstName;
      this.updateForm.lastName = item.lastName;
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
          this.gender = gender.data.map((singleCategory) => {
            return {
              value: singleGender.id,
              label: singleGender.genderName,
            };
          });
          this.prefix = prefix.data.map((singlePrefix) => {
            return {
              value: singlePrefix.id,
              label: singlePriority.prefixName,
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
    axios
      .get("/api/v1/users")
      .then((response) => {
        this.users = response.data;
        console.log(this.users);
      })
      .catch((error) => console.log(error));
    //   .then(data =>this.ideas = data)
  },
  created() {
    this.getLookUps();
  },
};
</script>
