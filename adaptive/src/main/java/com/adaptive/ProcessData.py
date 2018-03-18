from datetime import datetime

val = """1988-08-29        956 system.zip
1976-09-16     126976 old-photos.tgz
1987-02-03     118784 logs.rar
1961-12-04  703594496 very-long-filename.rar
1980-08-03          2 DELETE-THIS.TXT
2014-08-23        138 important.rar
2001-08-26     595968 MOONLIGHT-SONATA.FLAC
1967-11-30     245760 archive.rar
1995-10-13        731 file.tg"""


def solution(S):
	failure = "NO FILES"
	if S == None:
		return failure
	count = 0
	flag = False
	extension = set(["zip", "rar", "tgz"])
	month_limit = "1995-10-13"
	month_limit_dt = datetime.strptime(month_limit, '%Y-%M-%d')
	size_limit = long(240 * 1024)
	for val in S.splitlines():
		file_date_size = val.split();
		if len(file_date_size) != 3:
			continue
		each_date = file_date_size[0]
		datetime_object = datetime.strptime(each_date,
		                                    '%Y-%M-%d')
		if datetime_object > month_limit_dt:
			continue
		each_extension = file_date_size[2]
		index = each_extension.find('.')
		#print each_extension, index
		if index == -1:
			continue
		if each_extension[index + 1:] not in extension:
			continue
		each_size = long(file_date_size[1])
		if each_size < size_limit:
			continue
		count += 1

	if count == 0:
		return failure

	return str(count)

print solution(val)
