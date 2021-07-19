
import React, {useState, useEffect} from 'react';
import { render } from 'react-dom';
import { timeParse } from "d3-time-format";
import Chart from './Chart';
import styled from "styled-components";

const Tab = styled.button`
  padding: 5px 10px;
  cursor: pointer;
  opacity: 0.6;
  background: white;
  border: 0;
  outline: 0;
  border-bottom: 2px solid transparent;
  transition: ease border-bottom 250ms;
  ${({ active }) =>
	active &&
	`
    opacity: 1;
  `}
`;

const types = [
	{id: 1, title: "5 минут", urlParam: "http://localhost:8080/data?type=5m"},
	{id: 2, title: "1 час", urlParam: "http://localhost:8080/data?type=1h"},
	{id: 3, title: "1 день", urlParam: "http://localhost:8080/data?type=1d"},
	{id: 4, title: "1 неделя", urlParam: "http://localhost:8080/data?type=1w"},
	{id: 5, title: "все данные", urlParam: "http://localhost:8080/data?type=all"}
];

const parseDateTime = timeParse("%Y%m%d%H%M%S");

const tt = (d) => ({
	date: parseDateTime(d.date + d.time),
	open: +d.open,
	high: +d.high,
	low: +d.low,
	close: +d.close,
	volume: +d.vol,
});

const CharComponent = () => {

	const [active, setActive] = useState(types[0].title);
	const [url, setUrl] = useState(types[0].urlParam);
	const [data, setData] = useState([]);

	useEffect(() => {
		getData(url);
	}, []);

	const handleClick = (type) => {
		setActive(type.title);
		setUrl(type.urlParam);
		getData(type.urlParam);
	};

	function getData(url) {
		fetch(url)
			.then(response => response.json())
			.then((data) => data.map(tt))
			.then((data) => {
				setData(data)
			});
	}

	return (
		<div>
			<div>
				{types.map((type) => (
					<Tab
						key={type.title}
						active={active === type.title}
						onClick={() => handleClick(type)}
					>
						{type.title}
					</Tab>
				))}
			</div>
			{(data && data.length) ? <Chart data={data} /> : "Загрузка данных..."}
		</div>
	);
}

render(
	<CharComponent />,
	document.getElementById("react")
);

export default CharComponent;
