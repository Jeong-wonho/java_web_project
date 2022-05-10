const Hello = function () {
  function showName() {
    console.log("Mike");
  }
  function showAge(age) {
    console.log(age);
  }
  function showText(e) {
    console.log(e.target.value);
  }
  return (
    <div>
      <h1
        style={{
          color: "#f00",
          borderRight: "12px solid #000",
          marginBottom: "50px",
          opacity: 0.5,
        }}
      >
        Hello
      </h1>
      <button onClick={showName}>Show name</button>
      <button
        onClick={() => {
          showAge(10);
        }}
      >
        Show age
      </button>
      <input
        type="text"
        onChange={(e) => {
          const txt = e.target.value;
          showText(txt);
        }}
      />
    </div>
  );
};

export default Hello;
