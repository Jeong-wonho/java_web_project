import { useEffect, useState } from "react";
function Moneychange({ coin }) {
  const [dollar, setDollar] = useState(0);
  const [count, setCount] = useState(0);
  const onClick = (event) => {
    setCount(Math.floor(dollar / coin.quotes.USD.price));
    event.preventDefault();
  };
  const onChange = (event) => setDollar(event.target.value);
  return (
    <div>
      <form>
        <input
          onChange={onChange}
          type="text"
          placeholder="달러를 입력하세요"
        />
        <button onClick={onClick}> 변환 </button>
      </form>
      <h3>you can buy {count} coin </h3>
    </div>
  );
}
function App() {
  const [loading, setLoading] = useState(true);
  const [coins, setCoins] = useState([]);
  const [coin, setCoin] = useState({});
  const onClick = (event) => setCoin(coins[event.target.selectedIndex]);

  useEffect(() => {
    fetch("https://api.coinpaprika.com/v1/tickers?limit=1000")
      .then((response) => response.json())
      .then((json) => {
        setCoins(json);
        setLoading(false);
      })
      .then(() => setCoin(coins[0]));
  }, []);

  return (
    <div>
      <h1>The Coins!({coins.length})</h1>
      {loading ? <strong>Loading...</strong> : null}
      <select onChange={onClick}>
        {coins.map((coin) => (
          <option key={coin.id}>
            {coin.name} ({coin.symbol}) : {coin.quotes.USD.price} USD
          </option>
        ))}
      </select>
      <hr />
      {loading ? null : <Moneychange coin={coin} />}
    </div>
  );
}

export default App;
