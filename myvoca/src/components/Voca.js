import styles from "./Voca.module.scss";
import { useState, useEffect } from "react";
export default function Voca() {
  const [words, setWords] = useState([]);
  const [isMemory, setIsMemory] = useState(true);

  const getWords = async () => {
    const json = await (await fetch("http://localhost:3001/word")).json();
    setWords(json);
  };

  function memory() {
    setIsMemory(!isMemory);
  }

  useEffect(() => {
    getWords();
  }, []);

  return (
    <div className={styles.main}>
      <button onClick={memory}>외우기</button>
      <table>
        <thead>
          <tr>
            <td>English</td>
            <td>한글</td>
          </tr>
        </thead>
        <tbody>
          {words.map((word, idx) => (
            <tr key={idx}>
              <td>{word.english}</td>
              <td>
                {isMemory ? (
                  <div>
                    <button>know</button>
                    <button>뜻보기</button>
                  </div>
                ) : (
                  word.korean
                )}
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
