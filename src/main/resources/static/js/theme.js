
const btn = document.querySelector(".btn-toggle");

const theme = document.querySelector("#theme-link");

btn.addEventListener("click", function() {

  if (theme.getAttribute("href") == "/css/light.css") {
    theme.href = "/css/dark.css";
  } else {
    theme.href = "/css/light.css";
  }
});