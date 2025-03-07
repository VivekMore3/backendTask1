    package com.main.springBatch.config;

    import com.main.springBatch.entities.User;
    import com.main.springBatch.repositories.UserRepository;
    import org.springframework.batch.core.Job;
    import org.springframework.batch.core.Step;
    import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
    import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
    import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
    import org.springframework.batch.item.ItemProcessor;
    import org.springframework.batch.item.ItemWriter;
    import org.springframework.batch.item.file.FlatFileItemReader;
    import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
    import org.springframework.batch.item.file.mapping.DefaultLineMapper;
    import org.springframework.batch.item.file.separator.DefaultRecordSeparatorPolicy;
    import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.core.io.FileSystemResource;

    @Configuration
    @EnableBatchProcessing
    public class BatchConfig {

        @Autowired
        private JobBuilderFactory jobBuilderFactory;

        @Autowired
        private StepBuilderFactory stepBuilderFactory;

        @Autowired
        private UserRepository userRepository;

        // CSV Reader
        @Bean
        public FlatFileItemReader<User> reader() {
            FlatFileItemReader<User> reader = new FlatFileItemReader<>();
            reader.setResource(new FileSystemResource("src/main/resources/1000FakeUsers.csv")); // CSV file location
            reader.setLinesToSkip(1); // Skip header row
            reader.setRecordSeparatorPolicy(new DefaultRecordSeparatorPolicy());

            DefaultLineMapper<User> lineMapper = new DefaultLineMapper<>();

            DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
            tokenizer.setNames( "firstName", "lastname", "emailId", "phoneNumber", "password");

            BeanWrapperFieldSetMapper<User> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
            fieldSetMapper.setTargetType(User.class);

            lineMapper.setLineTokenizer(tokenizer);
            lineMapper.setFieldSetMapper(fieldSetMapper);

            reader.setLineMapper(lineMapper);
            return reader;
        }

        // Processor (optional, modify data if needed)
        @Bean
        public ItemProcessor<User, User> processor() {
            return user -> user; // No modifications, simply pass the data
        }

        // Writer - Save to Database
        @Bean
        public ItemWriter<User> writer() {
            return users -> userRepository.saveAll(users);
        }

        // Step Definition
        @Bean
        public Step userStep() {
            return stepBuilderFactory.get("userStep")
                    .<User, User>chunk(10) // Process 10 records at a time
                    .reader(reader())
                    .processor(processor())
                    .writer(writer())
                    .build();
        }

        // Job Definition
        @Bean
        public Job importUserJob() {
            return jobBuilderFactory.get("importUserJob")
                    .start(userStep()) // Run Step
                    .build();
        }
    }
