export function displayTimeStamp(timeStamp: string) {
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
