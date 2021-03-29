const enter_room_buttons =  document.getElementsByClassName("enter-room-button");
for (let i = 0; i < enter_room_buttons.length; i++) {
  enter_room_buttons[i].addEventListener('click', (event) => {
    window.location.href='http://localhost:8080/chess-board?id=' + event.target.parentElement.id;
  });
}

const remove_room_buttons =  document.getElementsByClassName("remove-room-button");
for (let i = 0; i < remove_room_buttons.length; i++) {
  remove_room_buttons[i].addEventListener('click', (event) => {
    const is_remove = confirm('정말 삭제하시겠습니까?');
    if (is_remove === true) {
      window.location.href='http://localhost:8080/remove?id=' + event.target.parentElement.id;
    }
  });
}