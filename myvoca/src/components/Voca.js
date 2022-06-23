import styles from "./Voca.module.scss";
import { useState, useEffect } from "react";
export default function Voca() {
  const [words, setWords] = useState([]);
  const getWords = async () => {
    const json = await (await fetch("http://localhost:3001/word")).json();
    setWords(json);
  };

  useEffect(() => {
    getWords();
  }, []);

  return (
    <div className={styles.main}>
      <div className={styles.button}>
        <button>외우기</button>
        <button>테스트</button>
      </div>
      <table>
        <thead>
          <tr>
            <td>English</td>
            <td>한글</td>
          </tr>
        </thead>
        <tbody>
          {words.map((word, idx) => (
            <tr>
              <td>{word.english}</td>
              <td>{word.korean}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
