import styles from "./Voca.module.scss";
export default function Voca() {
  return (
    <div className={styles.main}>
      <table>
        <thead>
          <tr>
            <td>English</td>
            <td>한글</td>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>Apple</td>
            <td>사과</td>
          </tr>
        </tbody>
      </table>
    </div>
  );
}
