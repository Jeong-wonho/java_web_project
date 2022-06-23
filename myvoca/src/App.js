import styles from "./App.module.css";
import Header from "./components/Header";
import Footer from "./components/Footer";
import Voca from "./components/Voca";
function App() {
  return (
    <div className={styles.main}>
      <Header />
      <Voca />
      <Footer />
    </div>
  );
}

export default App;
