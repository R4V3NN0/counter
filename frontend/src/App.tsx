import { useEffect, useState } from 'react'
import { Button, ButtonGroup, Typography, Container, Alert } from '@mui/material'
import { displayTimeStamp } from './utils'

function App() {
  const [count, setCount] = useState(0)
  const [timeStamp, setTimeStamp] = useState('')
  const [showSuccess, setShowSuccess] = useState(false)
  const [decrementable, setDecrementable] = useState(false)

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
    setDecrementable(true)
  }

  async function decrementCounter() {
    if (count <= 0) {
      setDecrementable(false)
      return
    }
    await fetch('http://localhost:8080/count/dec/', {
      method: 'post',
    })
    getCounter()
  }

  async function resetCounter() {
    await fetch('http://localhost:8080/count/', {
      method: 'delete',
    })
    await getCounter()
    setShowSuccess(true)
    setTimeout(() => {
      setShowSuccess(false)
    }, 3000)
  }

  return (
    <Container
      style={{
        height: '100vh',
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        justifyContent: 'center',
      }}
    >
      <Typography variant='h3' gutterBottom>
        {count}
      </Typography>
      <Typography variant='h6' gutterBottom>
        {displayTimeStamp(timeStamp)}
      </Typography>
      <ButtonGroup variant='contained' orientation='vertical'>
        <Button variant='contained' onClick={incrementCounter}>
          Zähler erhöhen
        </Button>
        <Button variant='contained' onClick={decrementCounter}>
          Zähler dekrementieren
        </Button>
        <Button variant='contained' onClick={resetCounter}>
          Zähler zurücksetzen
        </Button>
      </ButtonGroup>
      {showSuccess && (
        <Alert severity='success' variant='outlined' style={{ marginTop: 16 }}>
          Der Zähler wurde erfolgreich zurückgesetzt!
        </Alert>
      )}
      {!decrementable && (
        <Alert severity='error' variant='outlined' style={{ marginTop: 16 }}>
          Der Zähler kann nicht unter 0!
        </Alert>
      )}
    </Container>
  )
}

export default App
