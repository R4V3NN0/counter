import { useEffect, useState } from 'react'
import './App.css'

function App() {
  const [count, setCount] = useState(0)
  const [timeStamp, setTimeStamp] = useState('')

  async function getCounter() {
    const response = await fetch('http://localhost:8080/count')
    const data = await response.json()
    setCount(data.value)
    setTimeStamp(data.timeStamp)
  }

  useEffect(() => {
    getCounter()
  }, [])

  async function incrementCounter() {
    await fetch('http://localhost:8080/count/', {
      method: 'post',
    })
    getCounter()
  }

  async function resetCounter() {
    await fetch('http://localhost:8080/count/', {
      method: 'delete',
    })
    getCounter()
  }

  function displayTimeStamp() {
    const timeStampDate = new Date(timeStamp)
    const timezoneOffset = Math.abs(timeStampDate.getTimezoneOffset())
    timeStampDate.setMinutes(timeStampDate.getMinutes() + timezoneOffset)
    const day = timeStampDate.getDate()
    const month = timeStampDate.getMonth() + 1
    const year = timeStampDate.getFullYear()
    const hours = timeStampDate.getHours()
    const minutes = timeStampDate.getMinutes()
    const seconds = timeStampDate.getSeconds()
    return `${day}.${month}.${year} | ${hours}:${minutes}:${seconds}`
  }

  return (
    <div className='App'>
      <span className='count'>{count}</span>
      <span>{displayTimeStamp()}</span>
      <div className='button-wrapper'>
        <button className='button' onClick={incrementCounter}>
          Count erhöhen
        </button>
        <button className='button' onClick={resetCounter}>
          Count resetten
        </button>
      </div>
    </div>
  )
}

export default App
