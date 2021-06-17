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
            @click="loadModal(item)"
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
          <CButton @click="warningModal = false" color="success">Cancel</CButton>
          <CButton type="submit" color="success" @click="updateNote"
            >Edit</CButton
          >
        </template>
      </CModal>
       <CModal title="Delete Note" color="danger" :show.sync="dangerModal">
              <b>Are you sure you want to delete this Note?</b>
              <template #footer>
                <CButton @click="dangerModal = false" color="danger">Cancel</CButton>
                <CButton type="submit" color="danger" @click="deleteNote(item)"
                  >Delete</CButton
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
      dangerModal: false,
      findNote: "",
      removeNote:[],
      updatedNote: {},
      updateForm: {
        id: "",
        content: "",
      },
    };
  },
  methods: {
    loadModal(item){
      this.dangerModal=true;
      this.item = item;
    },
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
        this.dangerModal = false
        this.notes = this.notes.filter(note=>note.id!==deletingNote)
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
          this.warningModal = false
          this.findNote = this.notes.findIndex(note=>note.id===response.data.id)
          this.updatedNote = {
            id: response.data.id,
            content: response.data.content,
        };
          this.notes.splice(this.findNote,1,this.updatedNote)
          return this.notes
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
