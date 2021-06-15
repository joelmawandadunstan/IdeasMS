 <template>
  <CCard>
    <h1>List Of Notes</h1>
    <CDataTable
      :items="notes"
      :fields="fields"
      striped
      caption="Notes Table"
      :items-per-page="5"
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
                propagateNote(item);
              }
            "
          >
            Edit
          </CButton>&#160;
          <CButton
            color="danger"
            square
            size="sm"
            @click="deleteNote(item)"
          >
            Delete
          </CButton>&#160;
         
          <CButton >
            <router-link class="btn btn-success" to="/base/ideas" role="button"
              >Back to Ideas</router-link
            >
          </CButton>
        </td>
      </template>
    </CDataTable>
    <div>
      <CModal title="Update Note" color="success" :show.sync="warningModal">
         <CInput
          v-model="updateForm.content"
          placeholder="name"
          type="text"
          autocomplete="name"
        >
          <template #prepend-content><CIcon name="cil-user" /></template>
        </CInput>
        <!-- <form>
          <div class="form-group">
            <label for="exampleFormControlFile1">file Upload</label>
            <input
              type="file"
              class="form-control-file"
              id="exampleFormControlFile1"
            />
          </div>
        </form> -->

        <CInput v-model="updateForm.id" placeholder="id" type="hidden" />
        <template #footer>
          <CButton type="submit" color="success">Cancel</CButton>
          <CButton type="submit" color="success" @click="updateNote"
            >Edit</CButton
          >
        </template>
      </CModal>
    </div>
    
  </CCard>
</template>

<script>
//import usersData from "../users/UsersData";
import axios from "axios";
export default {
  name: "notes",
  components: {},
  data() {
    return {
      notes: [],
      fields: ["content", { key: "Actions" }],
      warningModal: false,
      updateForm: {
        id: "",
        content: "",
      },
    };
  },
  methods: {
    deleteNote(item) {
      let deletingNote = (item) = item.id;
      console.log(deletingNote);
      axios.delete("/api/v1/notes/delete/" + deletingNote, {
        headers: {
          "content-type": "application/json",
          Authorization: `Bearer ${localStorage.getItem("accessToken")}`,
        },
      })
      .then((response) => {
        // Event.fire("updated");
      })
      .catch((error) => {
        console.error(error);
      });
    },
    propagateNote(item) {
      this.updateForm.content = item.content;
      this.updateForm.id = item.id; //Do i have to update the id
    },
     updateNote() {
      axios
        .patch("/api/v1/notes/edit/" + this.updateForm.id, this.updateForm,
        {
           headers: {
             "content-type": "application/json",
      
      Authorization: `Bearer ${localStorage.getItem("accessToken")}`,
    },
        })
        .then((response) => {
          // Event.fire("updated");
        })
        .catch((error) => {
          console.error(error);
        });
    },
  },
  mounted() {
    axios
      .get("/api/v1/notes", {
        headers: {
          "content-type": "application/json",
          Authorization: `Bearer ${localStorage.getItem("accessToken")}`,
        },
      })
      .then((response) => {
        this.notes = response.data;
        console.log(this.notes);
      })
      .catch((error) => console.log(error));
  },
};
// response.json()
// .
</script>
