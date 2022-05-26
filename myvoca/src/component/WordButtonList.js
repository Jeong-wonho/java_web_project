import { useState } from "react";
import styles from "./WordButtonList.module.css";
import { Button } from "react-bootstrap";

export default function WordButtonList() {
  const [title, setTitle] = useState("영어단어");

  return (
    <div className={styles.wordlist}>
      <div>
        <Button className={styles.wordButton}>{title}</Button>
        <Button className={styles.wordButton}>버튼1-2</Button>
      </div>
      <div>
        <Button className={styles.wordButton}>버튼2-1</Button>
        <Button className={styles.wordButton}>버튼2-2</Button>
      </div>
    </div>
  );
}
