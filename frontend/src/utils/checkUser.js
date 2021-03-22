function isUser() {
  return (
    localStorage.getItem('accessToken') !== null &&
    localStorage.getItem('userId') !== null
  );
}

function isSameUser(id) {
  return (
    localStorage.getItem('userId') !== null &&
    localStorage.getItem('userId') === id
  );
}

export { isUser, isSameUser };
