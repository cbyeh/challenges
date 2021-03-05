/** Get an integer represented by date. Return null if badly formed or not using delim */
let getTimePassed = (datum, delim) => {
  // Check if there is a year, month, and day
  let split = datum.split(delim);
  if (split.length != 3) {
    return null;
  }
  // Check if they are convertible to integers and within reasonable ranges
  for (let i = 0; i < 3; i++) {
    let toInt = parseInt(split[i]);
    if (isNaN(split[i]) || isNaN(toInt)) {
      return null;
    }
    split[i] = toInt;
  }
  if (
    split[0] < 0 ||
    split[1] > 12 ||
    split[1] < 1 ||
    split[2] > 31 ||
    split[2] < 1
  ) {
    return null;
  }
  // Treat a year as 366 days, a month as 31 days. Use this rather than Epoch time as it is faster and still deterministic
  return [
    split[0] * 366 + split[1] * 31 + split[2],
    split[0],
    split[1],
    split[2],
  ]; // Return [time passed, year, month, day]
};

/** Sort dataArray by valid dates, uses delim, and whether ascending or descending */
let parseAndSortDates = (dataArray, delim, isAsc) => {
  let validDates = new Map(); // A hashmap of valid entries in dataArray and its parsed information
  let timesToDate = new Map(); // A hashmap of valid times to an entry in dataArray
  let times = [];
  // Find valid dates and their time
  for (let i = 0; i < dataArray.length; i++) {
    let parsedDate = getTimePassed(dataArray[i], delim);
    if (parsedDate != null) {
      validDates[dataArray[i]] = parsedDate;
      timesToDate[parsedDate[0]] = dataArray[i];
      times.push(parsedDate[0]);
    }
  }
  // Sort dates by time in ascending order
  times.sort((a, b) => a - b);
  // If descending, iterate array backwards
  let sol = [];
  if (!isAsc) {
    for (let i = times.length - 1; i >= 0; i--) {
      let date = validDates[timesToDate[times[i]]];
      sol.push(new Date(date[1], date[2] - 1, date[3])); // Create a date with year, month, and day. Months are 0-11
    }
    return sol;
  }
  // Else return in ascending order
  for (let i = 0; i < times.length; i++) {
    let date = validDates[timesToDate[times[i]]];
    sol.push(new Date(date[1], date[2] - 1, date[3]));
  }
  return sol;
};

// Test cases
var dataArray = [
  '2021/01/02',
  '1990-02-03',
  '2001-03-04',
  '1990~05~28',
  'junk',
];
var delim = '-';
var isAsc = false;
var output = parseAndSortDates(dataArray, delim, isAsc);

console.log(output);
