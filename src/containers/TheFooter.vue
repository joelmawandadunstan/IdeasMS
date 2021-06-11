<template>
  <CFooter :fixed="false">
    <div>
      <a href="#" target="_blank">flyHub Uganda</a>
      <span class="ml-1">&copy; {{new Date().getFullYear()}} Stanbic Bank UG.</span>
    </div>
    <div class="mfs-auto">
      <span class="mr-1" target="_blank">Powered by</span>
      <a href="#">flyHub Uganda</a>
    </div>
  </CFooter>
</template>

<script>
export default {
  name: 'TheFooter'
}
</script>
